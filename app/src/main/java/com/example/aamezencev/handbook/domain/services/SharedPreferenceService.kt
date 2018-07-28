package com.example.aamezencev.handbook.domain.services

import android.content.Context
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.google.gson.Gson

class SharedPreferenceService(private val applicationContext: Context,
                              private val gson: Gson) {
    private val SHARED_PREF_NAME = "handbook_shared_pref"
    private val DATABASE_FILE_PATH_KEY = "database_path"

    private val sharedPrefFile = applicationContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    private fun savedPathCount() =
            sharedPrefFile.all.filter { it.key.contains(DATABASE_FILE_PATH_KEY) }.size

    fun saveFilePath(databaseInfo: DatabaseInfo) {
        val databaseInfoGson = gson.toJson(databaseInfo)
        sharedPrefFile.edit()
                .putString(DATABASE_FILE_PATH_KEY + (savedPathCount() + 1), databaseInfoGson)
                .apply()
    }

    fun getFilePathList(): List<DatabaseInfo> {
        return sharedPrefFile.all
                .filter { it.key.contains(DATABASE_FILE_PATH_KEY) }
                .map { gson.fromJson(it.value.toString(), DatabaseInfo::class.java) }
    }

}