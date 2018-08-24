package com.example.aamezencev.handbook.presentation.hierarchy.list.router

import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract

class HierarchyRouter(private var module: HierarchyListContract.InputModule) :
        AbstractRouter<HierarchyListContract.RouterListener>(), HierarchyListContract.Router {
    private val CHAPTER = "chapter"
    private val PAGE = "page"

    override fun showChapter(androidComponent: AndroidComponent, parentId: Long) {
        androidComponent.fragmentManagerComponent
                .beginTransaction()
                .replace(R.id.hierarchy_container, module.createChapter(parentId))
                .addToBackStack(CHAPTER)
                .commit()
    }

    override fun showPage(androidComponent: AndroidComponent, dataId: Long) {
        androidComponent.fragmentManagerComponent
                .beginTransaction()
                .replace(R.id.hierarchy_container, module.createPage(dataId))
                .addToBackStack(PAGE)
                .commit()
    }
}