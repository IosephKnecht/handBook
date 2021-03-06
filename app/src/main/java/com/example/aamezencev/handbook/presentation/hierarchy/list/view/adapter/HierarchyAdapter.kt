package com.example.aamezencev.handbook.presentation.hierarchy.list.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.databinding.ItemHierarchyBinding

class HierarchyAdapter : RecyclerView.Adapter<HierarchyAdapter.ViewHolder>() {
    var elementList = listOf<HierarchyElement>()
    var itemClickChapter: ((Long) -> Unit)? = null
    var itemClickPage: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = DataBindingUtil.inflate<ItemHierarchyBinding>(LayoutInflater.from(parent.context),
                R.layout.item_hierarchy, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = elementList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = elementList[position]
        holder.binding.hierarchyElement = element
        if (element.dataElement == null) holder.itemView.setOnClickListener { itemClickChapter?.invoke(element.id) }
        else holder.itemView.setOnClickListener { itemClickPage?.invoke(element.dataElement.id) }
    }

    class ViewHolder(val binding: ItemHierarchyBinding) : RecyclerView.ViewHolder(binding.root)
}