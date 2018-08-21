package com.example.aamezencev.handbook.domain.services

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import com.example.aamezencev.handbook.application.Constants.DATABASE_NAME
import com.example.aamezencev.handbook.application.Constants.DATABASE_PATH
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DatabaseLoaderService(private val applicationContext: Context) {
    fun copyDatabase(inputStream: InputStream?): Observable<Unit> {
        return folderExist()
            .flatMap { fileExist() }
            .flatMap { copy(inputStream) }
    }

    fun copyDatabase(uri: Uri?): Observable<Unit> {
        return folderExist()
            .flatMap { fileExist() }
            .flatMap { copy(uri) }
    }

    private fun fileExist(): Observable<Unit> {
        return lambda {
            val database = File(DATABASE_PATH + DATABASE_NAME)
            if (database.exists()) database.delete()
        }
    }

    private fun folderExist(): Observable<Unit> {
        return lambda {
            val folder = File(DATABASE_PATH)
            if (!folder.exists()) folder.mkdirs()
        }
    }

    private fun copy(uri: Uri?): Observable<Unit> {
        return Observable.fromCallable { obtainInputStream(uri) }
            .flatMap {
                lambda {
                    val outputStream = FileOutputStream(DATABASE_PATH + DATABASE_NAME)
                    val buffer = ByteArray(1024)
                    var length = 0
                    val line: () -> Int = {
                        length = it!!.read(buffer)
                        length
                    }
                    while (line() > 0) {
                        outputStream.write(buffer, 0, length)
                    }
                    outputStream.flush()
                    outputStream.close()
                    it!!.close()
                }
            }
    }

    @SuppressLint("Recycle")
    private fun obtainInputStream(uri: Uri?): InputStream? {
        with(applicationContext.contentResolver) {
            return openInputStream(uri)
        }
    }

    private fun copy(inputStream: InputStream?): Observable<Unit> {
        return lambda {
            val outputStream = FileOutputStream(DATABASE_PATH + DATABASE_NAME)
            val buffer = ByteArray(1024)
            var length = 0
            val line: () -> Int = {
                length = inputStream!!.read(buffer)
                length
            }
            while (line() > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream!!.close()
        }
    }

    private fun <T> lambda(block: () -> T): Observable<T> {
        return Observable.create {
            try {
                it.onNext(block())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun parseMetaData(cursor: Cursor?): Observable<DatabaseInfo> {
        return lambda {
            cursor?.takeIf { cursor.moveToFirst() }?.run {
                return@lambda DatabaseInfo().apply {
                    name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    size = cursor.getColumnIndex(OpenableColumns.SIZE).let { sizeIndex ->
                        cursor.takeIf { !it.isNull(sizeIndex) }.run {
                            cursor.getString(sizeIndex)
                        }.toLong()
                    }
                    uri = cursor.notificationUri
                }
            } ?: DatabaseInfo()
        }.doAfterTerminate { cursor?.close() }
    }
}