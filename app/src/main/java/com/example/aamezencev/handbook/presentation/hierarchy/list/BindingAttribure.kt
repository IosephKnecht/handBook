package com.example.aamezencev.handbook.presentation.hierarchy.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.aamezencev.a3dviewer.Facade
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.adapter.HierarchyAdapter

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

@BindingAdapter(value = ["provideFacade", "buildThreeDimensionalModel"])
fun FrameLayout.buildModel(facade: Facade, threeDimensionalModel: ThreeDimensionalModel?) {
    if (threeDimensionalModel != null) {
        this.addView(facade.buildSurfaceView(facade.buildModel(threeDimensionalModel.modelArray.inputStream()),
                facade.buildFloor(), facade.buildLight()))
    }
}