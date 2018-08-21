package com.example.aamezencev.handbook.presentation.bookmarks.interactor

import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract

class BookmarksInteractor(private val sharedPreferenceService: SharedPreferenceService,
                          private val databaseLoaderService: DatabaseLoaderService,
                          private val sessionInitializer: SessionInitializer<DaoSession>) :
    AbstractInteractor<BookmarksContract.Listener>(), BookmarksContract.Interactor {
    override fun getBookmarks() {
        discardResult(sharedPreferenceService.makeReactive { getBookmarkList() }) { listener, result ->
            result.data { listener?.onObtainBookmarks(this) }
            result.throwable { }
        }
    }

    override fun openDatabase(bookmarkInfo: BookmarkInfo) {
        discardResult(sharedPreferenceService.makeReactive { getDatabaseName() }
            .flatMap { bookmarkName ->
                sharedPreferenceService.makeReactive { getFilePathList() }
                    .map { list -> list.find { it.name == bookmarkName } }
            }
            .flatMap { databaseLoaderService.copyDatabase(it.uri) }
            .flatMap { sessionInitializer.initSesseion() }
            .doOnNext { AppDelegate.daoSession = it }) { listener, result ->
            result.data { listener!!.onOpenedDatabase(bookmarkInfo) }
        }
    }
}