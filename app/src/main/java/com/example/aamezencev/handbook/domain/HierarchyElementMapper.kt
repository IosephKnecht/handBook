package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.Chapter
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.data.Page
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
}