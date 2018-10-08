package com.example.aamezencev.handbook.presentation.bookmarks

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import java.io.InputStream

interface BookmarksContract {
    enum class State {
        IDLE,
        INIT,
    }

    interface ViewModel : MvpViewModel {
        var state: State
        var bookmarkList: List<BookmarkInfo>
            @Bindable get

        fun reset()
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainBookmarks()
        fun openBookmark(bookmarkInfo: BookmarkInfo)
        fun removeBookmark(bookmarkInfo: BookmarkInfo)
        fun removeAllBookmarks()
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainBookmarks(bookmarkList: List<BookmarkInfo>)
        fun onRemoveBookmark(bookmarkInfo: BookmarkInfo)
        fun onRemoveAllBookmarks()
        fun onOpenedDatabase(bookmarkInfo: BookmarkInfo)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getBookmarks()
        fun openDatabase(bookmarkInfo: BookmarkInfo)
        fun removeBookmark(bookmarkInfo: BookmarkInfo)
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun openBookmark(androidComponent: AndroidComponent, bookmarkInfo: BookmarkInfo)
    }
}