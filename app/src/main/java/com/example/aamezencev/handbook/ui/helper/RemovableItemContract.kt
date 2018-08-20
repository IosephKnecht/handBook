package com.example.aamezencev.handbook.ui.helper

import com.example.aamezencev.handbook.ui.view.RemovableItem

interface RemovableItemContract {

    interface RemovableViewHolder {
        val removableItem: RemovableItem
    }

    interface RemovableItemListener {
        fun onRemove(viewHolder: RemovableViewHolder,
                     direction: Int,
                     position: Int)
    }
}