package com.example.aamezencev.handbook.presentation.bookmarks

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo

interface BookmarksContract {
    interface ViewModel : MvpViewModel {
        var bookmarkList: List<BookmarkInfo>
            @Bindable get
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainBookmarks()
        fun removeBookmark(bookmarkInfo: BookmarkInfo)
        fun removeAllBookmarks()
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainBookmarks(bookmarkList: List<BookmarkInfo>)
        fun onRemoveBookmark(bookmarkInfo: BookmarkInfo)
        fun onRemoveAllBookmarks()
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getBookmarks()
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun openBookmark(androidComponent: AndroidComponent, bookmarkInfo: BookmarkInfo)
    }
}