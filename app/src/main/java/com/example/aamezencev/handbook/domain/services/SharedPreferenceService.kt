package com.example.aamezencev.handbook.domain.services

import android.content.Context
import android.content.SharedPreferences
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.google.gson.Gson

class SharedPreferenceService(applicationContext: Context,
                              private val gson: Gson) {
    private val SHARED_PREF_NAME = "handbook_shared_pref"
    private val BOOKMARK_PREF_NAME = "handbook_bookmark_pref"

    private val DATABASE_FILE_PATH_KEY = "database_path"
    private val BOOKMARK_PREF_KEY = "bookmark"

    private val databaseInfoFile = applicationContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val bookmarksInfoFile = applicationContext.getSharedPreferences(BOOKMARK_PREF_NAME, Context.MODE_PRIVATE)

    fun saveUniqueFilePath(databaseInfo: DatabaseInfo) {
        val databaseInfoGson = gson.toJson(databaseInfo)
        if (uniqueValue(databaseInfoFile, databaseInfoGson)) {
            with(databaseInfoFile.all.size) {
                databaseInfoFile.edit()
                    .putString(DATABASE_FILE_PATH_KEY + (this + 1), databaseInfoGson)
                    .apply()
            }
        }
    }

    fun getFilePathList(): List<DatabaseInfo> {
        return databaseInfoFile.all
            .filter { it.key.contains(DATABASE_FILE_PATH_KEY) }
            .map { gson.fromJson(it.value.toString(), DatabaseInfo::class.java) }
    }

    fun getBookmarkList(): List<BookmarkInfo> {
        return bookmarksInfoFile.all
            .filter { it.key.contains(BOOKMARK_PREF_KEY) }
            .map { gson.fromJson(it.value.toString(), BookmarkInfo::class.java) }
    }

    fun saveUniqueBookmark(bookmarkInfo: BookmarkInfo) {
        val bookmarkInfoGson = gson.toJson(bookmarkInfo)
        if (uniqueValue(bookmarksInfoFile, bookmarkInfoGson)) {
            with(bookmarksInfoFile.all.size) {
                bookmarksInfoFile.edit()
                    .putString("$BOOKMARK_PREF_KEY ${(this + 1)}_${bookmarkInfo.databaseName}", bookmarkInfoGson)
                    .apply()
            }
        }
    }

    fun removeBookmark(bookmarkInfo: BookmarkInfo) {
        val bookmarkInfoGson = gson.toJson(bookmarkInfo)
        bookmarksInfoFile.all
            .entries
            .find { it.value.toString() == bookmarkInfoGson }
            ?.let {
                bookmarksInfoFile.edit()
                    .remove(it.key)
                    .apply()
            }
    }

    fun removeFilePath(databaseInfo: DatabaseInfo) {
        val databaseInfoGson = gson.toJson(databaseInfo)
        databaseInfoFile.all
            .entries
            .find { it.value.toString() == databaseInfoGson }
            ?.let {
                databaseInfoFile.edit()
                    .remove(it.key)
                    .apply()
            }
    }

    private fun uniqueValue(sharedPrefFile: SharedPreferences, value: String) =
        sharedPrefFile.all.filter { it.value == value }.isEmpty()

}