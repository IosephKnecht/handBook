package com.example.aamezencev.handbook.presentation.log.list.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.databinding.ItemLogBinding
import com.hypertrack.hyperlog.DeviceLogModel

class LogListAdapter : RecyclerView.Adapter<LogListAdapter.ViewHolder>() {
    var logList: List<LogItemModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemLogBinding>(LayoutInflater.from(parent.context), R.layout.item_log, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = logList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.logItem = logList[position]
    }

    class ViewHolder(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root)
}