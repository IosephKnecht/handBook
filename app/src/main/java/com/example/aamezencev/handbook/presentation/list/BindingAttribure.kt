package com.example.aamezencev.handbook.presentation.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract

@BindingAdapter("setHierarchy")
fun RecyclerView.setHierarchy(hierarchyList: List<HierarchyElement>) {
    val adapter = this.adapter as HierarchyAdapter
    adapter.elementList = hierarchyList
    adapter.notifyDataSetChanged()
}

@BindingAdapter("setVisible")
fun TextView.setVisible(value: DataElement?) {
    this.apply {
        if (value == null) visibility = View.VISIBLE
        else visibility = View.INVISIBLE
    }
}

@BindingAdapter("createHyperLinks")
fun TextView.createHyperLinks(viewModel: HierarchyScreenContract.ViewModel) {
    val spannableStringBuilder = SpannableStringBuilder(viewModel.description)
    val click = object : ClickableSpan() {
        override fun onClick(widget: View?) {
            Toast.makeText(widget!!.context, "Click", Toast.LENGTH_LONG).show()
        }
    }
    viewModel.pointerList.forEach {
        spannableStringBuilder.setSpan(click, it.startIndex, it.finalIndex, URLSpan.PARCELABLE_WRITE_RETURN_VALUE)
    }
    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = spannableStringBuilder
}