package com.example.aamezencev.handbook.ui.helper

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class RemovableItemTouchHelper(dragDirs: Int,
                               swipeDirs: Int,
                               val listener: RemovableItemTouchHelperListener) : RemovableTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?) = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?,
                          direction: Int) {
        listener.onRemoved(viewHolder!!, direction, viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RemovableTouchHelper.RemovableViewHolder?, actionState: Int) {
        viewHolder?.let {
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(it.removableItem.foregroundView)
        }
    }

    override fun onChildDraw(c: Canvas?,
                             recyclerView: RecyclerView?,
                             viewHolder: RemovableTouchHelper.RemovableViewHolder?,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView,
            viewHolder?.run { removableItem.foregroundView },
            dX, dY, actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas?,
                                 recyclerView: RecyclerView?,
                                 viewHolder: RemovableTouchHelper.RemovableViewHolder?,
                                 dX: Float,
                                 dY: Float,
                                 actionState: Int,
                                 isCurrentlyActive: Boolean) {

        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView,
            viewHolder?.run { removableItem.foregroundView }, dX, dY,
            actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView?,
                           viewHolder: RemovableTouchHelper.RemovableViewHolder?) {
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(viewHolder?.run { removableItem.foregroundView })
    }

    interface RemovableItemTouchHelperListener {
        fun onRemoved(viewHolder: RecyclerView.ViewHolder,
                      direction: Int,
                      position: Int)
    }

}