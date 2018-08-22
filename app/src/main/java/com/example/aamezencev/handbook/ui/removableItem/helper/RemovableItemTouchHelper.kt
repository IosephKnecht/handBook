package com.example.aamezencev.handbook.ui.removableItem.helper

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class RemovableItemTouchHelper(dragDirs: Int,
                               swipeDirs: Int,
                               val listener: RemovableItemContract.RemovableItemListener) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?) = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?,
                          direction: Int) {
        listener.onRemove(isRemovableVH(viewHolder)!!, direction, viewHolder!!.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        isRemovableVH(viewHolder)?.let {
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(it.removableItem.foregroundView)
        }
    }

    override fun onChildDraw(c: Canvas?,
                             recyclerView: RecyclerView?,
                             viewHolder: RecyclerView.ViewHolder?,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView,
            isRemovableVH(viewHolder)?.run { removableItem.foregroundView },
            dX, dY, actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas?,
                                 recyclerView: RecyclerView?,
                                 viewHolder: RecyclerView.ViewHolder?,
                                 dX: Float,
                                 dY: Float,
                                 actionState: Int,
                                 isCurrentlyActive: Boolean) {

        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView,
            isRemovableVH(viewHolder)?.run { removableItem.foregroundView }, dX, dY,
            actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView?,
                           viewHolder: RecyclerView.ViewHolder?) {
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(isRemovableVH(viewHolder)?.run { removableItem.foregroundView })
    }

    private fun isRemovableVH(viewHolder: RecyclerView.ViewHolder?): RemovableItemContract.RemovableViewHolder? {
        return viewHolder
            ?.takeIf { it is RemovableItemContract.RemovableViewHolder }
            ?.run { this as RemovableItemContract.RemovableViewHolder }
    }

}