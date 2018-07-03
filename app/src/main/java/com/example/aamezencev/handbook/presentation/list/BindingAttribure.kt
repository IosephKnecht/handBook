package com.example.aamezencev.handbook.presentation.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter

@BindingAdapter("setHierarchy")
fun RecyclerView.setHierarchy(hierarchyList: List<HierarchyElement>) {
    val adapter = this.adapter as HierarchyAdapter
    adapter.elementList = hierarchyList
    adapter.notifyDataSetChanged()
}

@BindingAdapter("setVisible")
fun TextView.setVisible(value: List<HierarchyElement>?) {
    this.apply {
        if (value == null) visibility = View.VISIBLE
        else visibility = View.INVISIBLE
    }
}