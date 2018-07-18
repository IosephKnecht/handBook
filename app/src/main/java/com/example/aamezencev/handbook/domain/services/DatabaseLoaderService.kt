package com.example.aamezencev.handbook.domain.services

import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DatabaseLoaderService {
    private val DATABASE_PATH = "/data/data/com.example.aamezencev.handbook/databases/"
    val DATABASE_NAME = "handBook-db"

    fun copyDatabase(inputStream: InputStream?): Observable<Unit> {
        return folderExist()
                .flatMap { fileExist() }
                .flatMap {
                    lambda {
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
}