package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.parcel.ParcelChapter
import com.example.aamezencev.handbook.data.presentation.Chapter
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM

object HierarchyElementMapper {
    fun fromView(element: IElement): HierarchyElementVM {
        return HierarchyElementVM().also {
            it.name = element.name
            it.childList = if (element is Chapter) element.childList.toMutableList()
            else mutableListOf()
            it.text = if (element is Page) element.text else ""
        }
    }


    fun fromParcelable(hierarchy: IElement): ParcelChapter {
        val name = hierarchy.name
        val childList = try {
            hierarchy.childList
        } catch (e: IllegalArgumentException) {
            listOf<IElement>()
        }
        val text = try {
            hierarchy.text
        } catch (e: IllegalArgumentException) {
            ""
        }
        return ParcelChapter(name, childList, text)
    }
}