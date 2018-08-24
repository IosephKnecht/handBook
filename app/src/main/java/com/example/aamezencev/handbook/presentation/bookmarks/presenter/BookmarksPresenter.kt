package com.example.aamezencev.handbook.presentation.bookmarks.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.domain.utils.ColorUtil
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract

class BookmarksPresenter(private val interactor: BookmarksContract.Interactor,
                         private val router: BookmarksContract.Router) : AbstractPresenter<BookmarksContract.ViewModel>(),
    BookmarksContract.Presenter, BookmarksContract.Listener, BookmarksContract.RouterListener {

    override fun attachView(viewModel: BookmarksContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
    }

    override fun obtainBookmarks() {
        interactor.getBookmarks()
    }

    override fun removeBookmark(bookmarkInfo: BookmarkInfo) {
        interactor.removeBookmark(bookmarkInfo)
    }

    override fun removeAllBookmarks() {
    }

    override fun openBookmark(bookmarkInfo: BookmarkInfo) {
        interactor.openDatabase(bookmarkInfo)
    }

    override fun onObtainBookmarks(bookmarkList: List<BookmarkInfo>) {
        viewModel!!.bookmarkList = ColorUtil.instanceColor(androidComponent!!.activityComponent, bookmarkList)
    }

    override fun onRemoveBookmark(bookmarkInfo: BookmarkInfo) {
    }

    override fun onRemoveAllBookmarks() {

    }

    override fun onOpenedDatabase(bookmarkInfo: BookmarkInfo) {
        router.openBookmark(androidComponent!!, bookmarkInfo)
    }
}