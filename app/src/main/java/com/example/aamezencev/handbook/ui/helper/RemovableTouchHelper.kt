package com.example.aamezencev.handbook.ui.helper

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.aamezencev.handbook.ui.view.RemovableItem

class RemovableTouchHelper(callback: SimpleCallback) : ItemTouchHelper(callback) {

    abstract class SimpleCallback(dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

        final override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            onSelectedChanged(viewHolder as RemovableViewHolder?, actionState)
        }

        open fun onSelectedChanged(viewHolder: RemovableViewHolder?, actionState: Int) {
        }

        final override fun onChildDraw(c: Canvas?,
                                       recyclerView: RecyclerView?,
                                       viewHolder: RecyclerView.ViewHolder?,
                                       dX: Float,
                                       dY: Float,
                                       actionState: Int,
                                       isCurrentlyActive: Boolean) {
            onChildDraw(c, recyclerView, viewHolder as RemovableViewHolder?, dX, dY, actionState, isCurrentlyActive)
        }

        open fun onChildDraw(c: Canvas?,
                             recyclerView: RecyclerView?,
                             viewHolder: RemovableViewHolder?,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        }

        final override fun onChildDrawOver(c: Canvas?,
                                           recyclerView: RecyclerView?,
                                           viewHolder: RecyclerView.ViewHolder?,
                                           dX: Float,
                                           dY: Float,
                                           actionState: Int,
                                           isCurrentlyActive: Boolean) {
            onChildDrawOver(c, recyclerView, viewHolder as RemovableViewHolder?, dX, dY, actionState, isCurrentlyActive)
        }

        open fun onChildDrawOver(c: Canvas?,
                                 recyclerView: RecyclerView?,
                                 viewHolder: RemovableViewHolder?,
                                 dX: Float,
                                 dY: Float,
                                 actionState: Int,
                                 isCurrentlyActive: Boolean) {
        }

        final override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
            clearView(recyclerView, viewHolder as RemovableViewHolder?)
        }

        open fun clearView(recyclerView: RecyclerView?, viewHolder: RemovableViewHolder?) {
        }
    }

    abstract class RemovableViewHolder(val removableItem: RemovableItem) : RecyclerView.ViewHolder(removableItem)
}