package com.example.aamezencev.handbook.presentation.bookmarks.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.databinding.ItemBookmarkBinding
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemContract
import com.example.aamezencev.handbook.ui.removableItem.view.RemovableItem

class BookmarksAdapter : RecyclerView.Adapter<BookmarksAdapter.ViewHolder>(), RemovableItemContract.RemovableItemAdapter {

    var bookmarkList = mutableListOf<BookmarkInfo>()
    var clickListener: ((BookmarkInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val removableItem = RemovableItem(parent.context, R.layout.background_item, R.layout.item_bookmark)
        return ViewHolder(removableItem)
    }

    override fun getItemCount() = bookmarkList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = DataBindingUtil.bind<ItemBookmarkBinding>(holder.removableItem.foregroundView!!)
        with(bookmarkList[position]) {
            binding?.bookmark = this
            binding?.bookmarkPreview?.setColorFilter(color!!)
            holder.removableItem.setOnClickListener { clickListener?.invoke(this) }
            if (color != null) holder.removableItem.backgroundView?.setBackgroundColor(color!!)
        }
    }

    override fun removeItem(position: Int) {
        bookmarkList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(override val removableItem: RemovableItem) : RecyclerView.ViewHolder(removableItem), RemovableItemContract.RemovableViewHolder
}