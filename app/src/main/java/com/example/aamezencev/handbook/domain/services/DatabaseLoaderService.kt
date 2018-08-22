package com.example.aamezencev.handbook.domain.services

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
    fun copy(uri: Uri): Observable<Unit> {
        return Observable.fromCallable { folderExist() }
            .flatMap { Observable.fromCallable { fileExist() } }
            .flatMap { Observable.fromCallable { obtainInputStream(uri) } }
            .flatMap { Observable.fromCallable { copy(it) } }
    }

    fun parseMetaData(uri: Uri): Observable<DatabaseInfo> {
        return Observable.fromCallable { obtainCursor(uri) }
            .flatMap {
                Observable.fromCallable { parseMetaData(it, uri) }
                    .doAfterTerminate { it.close() }
            }
    }


    private fun obtainInputStream(uri: Uri): InputStream {
        with(applicationContext.contentResolver) {
            return openInputStream(uri)
        }
    }

    private fun obtainCursor(uri: Uri): Cursor {
        with(applicationContext.contentResolver) {
            return query(uri, null, null, null, null, null)
        }
    }


    private fun fileExist() {
        val database = File(DATABASE_PATH + DATABASE_NAME)
        if (database.exists()) database.delete()
    }

    private fun folderExist() {
        val folder = File(DATABASE_PATH)
        if (!folder.exists()) folder.mkdirs()
    }

    private fun copy(inputStream: InputStream) {
        val outputStream = FileOutputStream(DATABASE_PATH + DATABASE_NAME)
        val buffer = ByteArray(1024)
        var length = 0
        val line: () -> Int = {
            length = inputStream.read(buffer)
            length
        }
        while (line() > 0) {
            outputStream.write(buffer, 0, length)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    private fun parseMetaData(cursor: Cursor, parsingUri: Uri): DatabaseInfo {
        return cursor.takeIf { it.moveToFirst() }?.run {
            DatabaseInfo().apply {
                name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                size = cursor.getColumnIndex(OpenableColumns.SIZE).let { sizeIndex ->
                    cursor.takeIf { !it.isNull(sizeIndex) }.run {
                        cursor.getString(sizeIndex)
                    }.toLong()
                }
                uri = parsingUri
            }
        } ?: DatabaseInfo()
    }
}