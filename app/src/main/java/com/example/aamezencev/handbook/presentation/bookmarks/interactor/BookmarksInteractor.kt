package com.example.aamezencev.handbook.presentation.bookmarks.interactor

import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class BookmarksInteractor(private val sharedPreferenceService: SharedPreferenceService,
                          private val databaseLoaderService: DatabaseLoaderService,
                          private val sessionInitializer: SessionInitializer<DaoSession>) :
    AbstractInteractor<BookmarksContract.Listener>(), BookmarksContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getBookmarks() {
        discardResult(sharedPreferenceService.makeReactive { getBookmarkList() }) { listener, result ->
            result.data { listener?.onObtainBookmarks(this) }
            result.throwable { }
        }
    }

    override fun openDatabase(bookmarkInfo: BookmarkInfo) {
        compositeDisposable.add(discardResult(sharedPreferenceService.makeReactive { getDatabaseName() }
            .flatMap { cachedName ->
                if (bookmarkInfo.databaseName == cachedName && AppDelegate.daoSession != null) Observable.just(bookmarkInfo) else
                    sharedPreferenceService.makeReactive { getFilePathList() }
                        .map { list -> list.find { it.name == bookmarkInfo.databaseName } }
                        .flatMap { databaseLoaderService.copy(it.uri) }
                        .flatMap { sessionInitializer.initSesseion() }
                        .doOnNext { AppDelegate.daoSession = it }
            }) { listener, result ->
            result.data { listener!!.onOpenedDatabase(bookmarkInfo) }
        })
    }

    override fun removeBookmark(bookmarkInfo: BookmarkInfo) {
        sharedPreferenceService.removeBookmark(bookmarkInfo)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}