package com.example.aamezencev.handbook.ui.removableItem.helper

import com.example.aamezencev.handbook.ui.removableItem.view.RemovableItem

interface RemovableItemContract {

    interface RemovableViewHolder {
        val removableItem: RemovableItem
    }

    interface RemovableItemAdapter {
        fun removeItem(position: Int)
    }

    interface RemovableItemListener {
        fun onRemove(viewHolder: RemovableViewHolder,
                     direction: Int,
                     position: Int)
    }
}