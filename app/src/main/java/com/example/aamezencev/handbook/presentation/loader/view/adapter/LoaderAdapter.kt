package com.example.aamezencev.handbook.presentation.loader.view.adapter

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.databinding.ItemDatabaseBinding
import com.example.aamezencev.handbook.domain.utils.SizeUtil

class LoaderAdapter : RecyclerView.Adapter<LoaderAdapter.ViewHolder>() {
    var databaseList: List<DatabaseInfo> = listOf()
    var clickListener: ((Uri) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemDatabaseBinding>(LayoutInflater.from(parent.context),
            R.layout.item_database, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = databaseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(databaseList[position]) {
            holder.binding.name.text = name
            holder.binding.size.text = SizeUtil.sizeToString(size)
            holder.binding.root.setOnClickListener { clickListener?.invoke(uri) }
        }
    }

    class ViewHolder(val binding: ItemDatabaseBinding) : RecyclerView.ViewHolder(binding.root)
}