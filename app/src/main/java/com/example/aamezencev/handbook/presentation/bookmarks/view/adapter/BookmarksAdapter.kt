package com.example.aamezencev.handbook.presentation.bookmarks.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.databinding.ItemBookmarkBinding

class BookmarksAdapter : RecyclerView.Adapter<BookmarksAdapter.ViewHolder>() {

    var bookmarkList = mutableListOf<BookmarkInfo>()
    var clickListener: ((BookmarkInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingItem = DataBindingUtil.inflate<ItemBookmarkBinding>(LayoutInflater.from(parent.context),
            R.layout.item_bookmark,
            parent,
            false)
        return ViewHolder(bindingItem)
    }

    override fun getItemCount() = bookmarkList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingItem.bookmark = bookmarkList[position]
        holder.itemView.setOnClickListener { clickListener?.invoke(bookmarkList[position]) }
    }

    class ViewHolder(val bindingItem: ItemBookmarkBinding) : RecyclerView.ViewHolder(bindingItem.root)
}