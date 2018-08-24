package com.example.aamezencev.handbook.presentation.bookmarks

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.presentation.bookmarks.view.adapter.BookmarksAdapter
import java.util.*

@BindingAdapter("setBookmarkList")
fun RecyclerView.setBookmarkList(bookmarkList: List<BookmarkInfo>) {
    (this.adapter as BookmarksAdapter).bookmarkList = bookmarkList.toMutableList()
    this.adapter.notifyDataSetChanged()
}