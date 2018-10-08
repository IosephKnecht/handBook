package com.example.aamezencev.handbook.presentation.bookmarks.router

import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract

class BookmarksRouter(private val module: HierarchyListContract.InputModule) : AbstractRouter<BookmarksContract.RouterListener>(),
    BookmarksContract.Router {

    override fun openBookmark(androidComponent: AndroidComponent, bookmarkInfo: BookmarkInfo) {
        androidComponent.activityComponent
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack(bookmarkInfo.databaseName)
            .replace(R.id.hierarchy_container, module.createPage(bookmarkInfo.dataHierarchyId, bookmarkInfo.pageNumber))
            .commit()
    }
}