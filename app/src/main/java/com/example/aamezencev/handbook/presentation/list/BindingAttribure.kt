package com.example.aamezencev.handbook.presentation.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter

@BindingAdapter("setHierarchy")
fun RecyclerView.setHierarchy(hierarchyList: List<IElement>) {
    val adapter = this.adapter as HierarchyAdapter
    adapter.elementList = hierarchyList
}