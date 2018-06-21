package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.parcel.ParcelHierarchy
import com.example.aamezencev.handbook.data.presentation.Chapter
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM

object HierarchyElementMapper {
    fun fromView(element: IElement<IHierarchy>): HierarchyElementVM {
        return HierarchyElementVM().also {
            it.name = element.name
            it.childList = if (element is Chapter) element.childList.toMutableList()
            else mutableListOf()
            it.text = if (element is Page) element.text else ""
        }
    }


    fun fromParcelable(hierarchy: IHierarchy): ParcelHierarchy {
        val name = hierarchy.name
        val childList = try {
            hierarchy.childList
        } catch (e: IllegalArgumentException) {
            listOf<IHierarchy>()
        }
        val text = try {
            hierarchy.text
        } catch (e: IllegalArgumentException) {
            ""
        }
        return ParcelHierarchy(name, childList, text)
    }
}