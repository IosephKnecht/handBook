package com.example.aamezencev.handbook.presentation.loader

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.loader.view.adapter.LoaderAdapter

@BindingAdapter("setDatabaseInfo")
fun RecyclerView.setDatabaseInfo(list: List<DatabaseInfo>) {
    (this.adapter as LoaderAdapter).databaseList = list
    this.adapter.notifyDataSetChanged()
}