package com.example.aamezencev.handbook.presentation.log

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.presentation.log.list.view.adapter.LogListAdapter
import com.hypertrack.hyperlog.DeviceLogModel

@BindingAdapter("setLogs")
fun RecyclerView.setLogs(logList: List<LogItemModel>) {
    with(this.adapter as LogListAdapter) {
        this.logList = logList
        notifyDataSetChanged()
    }
}