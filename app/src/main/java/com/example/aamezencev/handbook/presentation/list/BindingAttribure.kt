package com.example.aamezencev.handbook.presentation.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter

@BindingAdapter("setHierarchy")
fun RecyclerView.setHierarchy(hierarchyList: List<IElement>) {
    val adapter = this.adapter as HierarchyAdapter
    adapter.elementList = hierarchyList
}

@BindingAdapter("setVisible")
fun TextView.setVisible(list: MutableList<IElement>) {
    this.apply {
        if (list.isNotEmpty()) visibility = View.VISIBLE
        else visibility = View.INVISIBLE
    }
}