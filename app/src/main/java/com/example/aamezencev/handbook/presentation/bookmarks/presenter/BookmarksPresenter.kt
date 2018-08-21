package com.example.aamezencev.handbook.presentation.bookmarks.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract

class BookmarksPresenter(private val interactor: BookmarksContract.Interactor,
                         private val router: BookmarksContract.Router) : AbstractPresenter<BookmarksContract.ViewModel>(),
    BookmarksContract.Presenter, BookmarksContract.Listener, BookmarksContract.RouterListener {
    override fun obtainBookmarks() {
        interactor.getBookmarks()
    }

    override fun removeBookmark(bookmarkInfo: BookmarkInfo) {
    }

    override fun removeAllBookmarks() {
    }

    override fun openBookmark(bookmarkInfo: BookmarkInfo) {
        interactor.openDatabase(bookmarkInfo)
    }

    override fun onObtainBookmarks(bookmarkList: List<BookmarkInfo>) {
        viewModel!!.bookmarkList = bookmarkList
    }

    override fun onRemoveBookmark(bookmarkInfo: BookmarkInfo) {
    }

    override fun onRemoveAllBookmarks() {

    }

    override fun onOpenedDatabase(bookmarkInfo: BookmarkInfo) {
        router.openBookmark(androidComponent!!, bookmarkInfo)
    }
}