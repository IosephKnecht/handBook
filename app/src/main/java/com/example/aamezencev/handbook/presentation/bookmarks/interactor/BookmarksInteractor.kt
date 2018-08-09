package com.example.aamezencev.handbook.presentation.bookmarks.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import io.reactivex.Observable

class BookmarksInteractor(private val sharedPreferenceService: SharedPreferenceService) :
    AbstractInteractor<BookmarksContract.Listener>(), BookmarksContract.Interactor {
    override fun getBookmarks() {
        discardResult(sharedPreferenceService.makeReactive { getBookmarkList() }) { listener, result ->
            result.data { listener?.onObtainBookmarks(this) }
            result.throwable { }
        }
    }
}