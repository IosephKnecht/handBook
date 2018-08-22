package com.example.aamezencev.handbook.presentation.bookmarks.viewModel

import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract

class BookmarkViewModel : AbstractViewModel(), BookmarksContract.ViewModel {
    override var bookmarkList: List<BookmarkInfo> = mutableListOf()
        set(value) {
            field = value
            notifyChange()
        }
}