package com.example.aamezencev.handbook.presentation.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.IElement

class HierarchyAdapter : RecyclerView.Adapter<HierarchyAdapter.ViewHolder>() {
    var elementList = listOf<IElement>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = elementList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = elementList[position]
        holder.title.text = element.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView

        init {
            title = itemView.findViewById(R.id.titleTextView)
        }
    }
}