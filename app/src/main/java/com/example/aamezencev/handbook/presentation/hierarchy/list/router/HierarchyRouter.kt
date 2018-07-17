package com.example.aamezencev.handbook.presentation.hierarchy.list.router

import android.support.v4.app.FragmentActivity
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.presentation.hierarchy.screen.view.ViewPagerContainer

class HierarchyRouter(private var context: FragmentActivity?) {
    fun clickChapter(parentId: Long) {
        context?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(parentId))
                ?.addToBackStack("hierarchy")
                ?.commit()
    }

    fun clickPage(dataId: Long) {
        context?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.hierarchyContainer, ViewPagerContainer.instanceFragment(dataId))
                ?.addToBackStack("page")
                ?.commit()
    }
}