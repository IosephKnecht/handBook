package com.example.aamezencev.handbook.domain

import android.content.Context
import com.example.aamezencev.handbook.R
import java.io.File
import java.io.FileOutputStream

object DataBaseOpenHelper {
    private val DATABASE_PATH = "/data/data/com.example.aamezencev.handbook/databases/"
    val DATABASE_NAME = "handBook-db"

    @Throws(Throwable::class)
    fun copyDataBase(applicationContext: Context) {
        checkFolder()
        checkFile()

        try {
            val inputStream = applicationContext.resources.openRawResource(R.raw.init_db)

            val outputStream = FileOutputStream(DATABASE_PATH + DATABASE_NAME)
            val buffer = ByteArray(1024)
            var length = 0;
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
        } catch (e: Exception) {
            throw Throwable("copy database error")
        }
    }

    @Throws(Throwable::class)
    private fun checkFolder() {
        val folder = File(DATABASE_PATH)
        var addedFolder = true
        if (!folder.exists()) {
            try {
                addedFolder = folder.mkdirs()
            } catch (e: Exception) {
                throw Throwable("database folder error")
            }
        }
    }

    @Throws(Throwable::class)
    private fun checkFile() {
        val database = File(DATABASE_PATH + DATABASE_NAME)
        var existDatabase = true
        if (database.exists()) {
            try {
                database.delete()
            } catch (e: Exception) {
                throw Throwable("database delete error")
            }
        }
    }
}