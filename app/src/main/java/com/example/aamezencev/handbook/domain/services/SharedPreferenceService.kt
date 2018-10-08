package com.example.aamezencev.handbook.domain.services

import android.content.Context
import android.content.SharedPreferences
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.google.gson.Gson
import io.reactivex.Observable

class SharedPreferenceService(applicationContext: Context,
                              private val gson: Gson) {
    private val DATABASE_PREF_NAME = "handbook_database_pref"
    private val BOOKMARK_PREF_NAME = "handbook_bookmark_pref"
    private val COMMON_SHAREDPRE_NAME = "handbook_shared_pref"

    private val DATABASE_FILE_PATH_KEY = "database_path"
    private val BOOKMARK_PREF_KEY = "bookmark"
    private val DATABASE_NAME = "database_name"

    private val databaseInfoFile = applicationContext.getSharedPreferences(DATABASE_PREF_NAME, Context.MODE_PRIVATE)
    private val bookmarksInfoFile = applicationContext.getSharedPreferences(BOOKMARK_PREF_NAME, Context.MODE_PRIVATE)
    private val commonSharedPref = applicationContext.getSharedPreferences(COMMON_SHAREDPRE_NAME, Context.MODE_PRIVATE)

    fun <T> makeReactive(block: SharedPreferenceService.() -> T): Observable<T> {
        return Observable.fromCallable { block() }
    }

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

    fun saveDatabaseName(name: String) {
        commonSharedPref.edit()
            .putString(DATABASE_NAME, name)
            .apply()
    }

    fun getDatabaseName() = commonSharedPref.getString(DATABASE_NAME, null)

    fun existBookmarkInDatabase(dataId: Long, pageNumber: Long) =
        getBookmarkList().find {
            it.dataHierarchyId == dataId &&
                it.pageNumber == pageNumber &&
                it.databaseName == getDatabaseName()
        } != null


    private fun uniqueValue(sharedPrefFile: SharedPreferences, value: String) =
        sharedPrefFile.all.filter { it.value == value }.isEmpty()

}