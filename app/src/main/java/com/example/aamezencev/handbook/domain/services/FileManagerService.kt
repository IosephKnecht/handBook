package com.example.aamezencev.handbook.domain.services

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import com.hypertrack.hyperlog.DeviceLogModel
import com.hypertrack.hyperlog.HyperLog
import com.hypertrack.hyperlog.utils.HLDateTimeUtility
import com.hypertrack.hyperlog.utils.Utils
import io.reactivex.Observable
import java.io.*
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class FileManagerService(private val applicationContext: Context) {
    private val authority = "com.example.aamezencev.handbook"
    private val filesDirectoryPath = ContextCompat.getDataDir(applicationContext)
        ?.absolutePath + File.separator + "files"

    fun getHyperLogFile(): Observable<List<Uri>> {
        return Observable.fromCallable { this.countList() }
            .flatMapIterable { list -> list }
            .flatMap { batchNumber ->
                Observable.fromCallable { deviceLogModels(batchNumber) }
                    .flatMap { logs -> Observable.fromCallable { deviceLogsToString(logs) } }
                    .flatMap { strList -> writeStringsToFile(strList, batchNumber) }
                    .map { file ->
                        return@map try {
                            zipFile(file)
                        } catch (e: IOException) {
                            e.printStackTrace()
                            File(Uri.EMPTY.path)
                        }
                    }
                    .map { zippedFile -> FileProvider.getUriForFile(applicationContext, authority, zippedFile) }
            }
            .toList()
            .toObservable()
            .doOnError { removeAllZipFiles() }
    }

    private fun writeStringsToFile(stringDeviceLogModels: List<String>, batchNumber: Int): Observable<File> {
        return Observable.create { emitter ->
            try {
                var fileName = HLDateTimeUtility.getCurrentTime()
                fileName = fileName.replace("[^a-zA-Z0-9_\\\\-\\\\.]".toRegex(), "_")
                fileName = fileName + "_" + batchNumber + ".txt"
                emitter.onNext(Utils.writeStringsToFile(applicationContext, stringDeviceLogModels, fileName))
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    private fun deviceLogModels(batchNumber: Int): List<DeviceLogModel> {
        return HyperLog.getDeviceLogs(true, batchNumber)
    }

    private fun deviceLogsToString(deviceLogModels: List<DeviceLogModel>): List<String> {
        val list = ArrayList<String>()
        deviceLogModels.forEach {
            list.add(it.deviceLog)
        }
        return list
    }

    private fun countList(): List<Int> {
        var batchCount = HyperLog.getDeviceLogBatchCount()
        val list = ArrayList<Int>()
        while (batchCount != 0) {
            list.add(batchCount)
            batchCount--
        }
        return list
    }

    @Throws(IOException::class)
    private fun zipFile(file: File): File {
        val BUFFER_SIZE = 8192

        val zipFileName = file.name.replace(".txt", ".zip")

        val zipFile = File(applicationContext.filesDir.absolutePath, zipFileName)
        val out = ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile)))

        try {
            val data = ByteArray(BUFFER_SIZE)
            val fi = FileInputStream(file)
            val origin = BufferedInputStream(fi, BUFFER_SIZE)
            try {
                val entry = ZipEntry(file.name)
                out.putNextEntry(entry)
                var count: Int
                do {
                    count = origin.read(data, 0, BUFFER_SIZE)
                    out.write(data, 0, count)
                } while (count != -1)
            } finally {
                origin.close()
            }
        } finally {
            out.flush()
            out.close()
        }

        return zipFile
    }

    private fun removeAllZipFiles() {
        val fileDirectory = File(filesDirectoryPath)
        val nestedFiles = fileDirectory.listFiles()
        for (i in nestedFiles!!.indices) {
            val temp = nestedFiles[i]
            if (temp.exists() && temp.name.contains(".zip")) temp.delete()
        }
    }
}