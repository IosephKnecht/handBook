package com.example.aamezencev.handbook.presentation.list.router

import android.support.v4.app.FragmentActivity
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.parcel.ParcelHierarchy
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.presentation.screen.PageFragment

class HierarchyRouter(private var context: FragmentActivity?) {
    fun clickChapter(hierarchy: ParcelHierarchy) {
        context?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(hierarchy))
                ?.commit()
    }

    fun clickPage(textPage: String) {
        context?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.hierarchyContainer, PageFragment.instanceFragment(textPage))
                ?.commit()
    }
}