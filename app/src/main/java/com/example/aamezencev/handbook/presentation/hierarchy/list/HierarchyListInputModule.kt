package com.example.aamezencev.handbook.presentation.hierarchy.list

import android.support.v4.app.Fragment
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.presentation.hierarchy.screen.view.ViewPagerContainer

class HierarchyListInputModule : HierarchyListContract.InputModule {
    override fun createChapter(parentId: Long): Fragment = HierarchyFragment.instanceFragment(parentId)
    override fun createPage(dataId: Long, position: Long): Fragment = ViewPagerContainer.instanceFragment(dataId, position)
}