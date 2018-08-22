package com.example.aamezencev.handbook.presentation.bookmarks

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.presentation.bookmarks.view.adapter.BookmarksAdapter

@BindingAdapter("setBookmarkList")
fun RecyclerView.setBookmarkList(bookmarkList: List<BookmarkInfo>) {
    (this.adapter as BookmarksAdapter).bookmarkList = bookmarkList.toMutableList()
    this.adapter.notifyDataSetChanged()
}