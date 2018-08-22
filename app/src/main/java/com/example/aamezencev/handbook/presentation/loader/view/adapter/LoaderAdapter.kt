package com.example.aamezencev.handbook.presentation.loader.view.adapter

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.databinding.ItemDatabaseBinding
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemContract
import com.example.aamezencev.handbook.ui.removableItem.view.RemovableItem

class LoaderAdapter : RecyclerView.Adapter<LoaderAdapter.ViewHolder>(), RemovableItemContract.RemovableItemAdapter {
    var databaseList: MutableList<DatabaseInfo> = mutableListOf()
    var clickListener: ((Uri) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val removableItem = RemovableItem(parent.context, R.layout.background_item, R.layout.item_database)
        return ViewHolder(removableItem)
    }

    override fun getItemCount() = databaseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = DataBindingUtil.bind<ItemDatabaseBinding>(holder.removableItem.foregroundView!!)
        with(databaseList[position]) {
            binding?.databaseInfo = this
            holder.removableItem.setOnClickListener { clickListener?.invoke(uri) }
        }
    }

    override fun removeItem(position: Int) {
        databaseList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(override val removableItem: RemovableItem) : RecyclerView.ViewHolder(removableItem),
            RemovableItemContract.RemovableViewHolder
}