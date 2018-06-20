package com.example.aamezencev.handbook.presentation.list.router

import android.support.v4.app.FragmentActivity
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.parcel.ParcelChapter
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.presentation.screen.PageFragment
import com.example.aamezencev.handbook.presentation.screen.PagerFragment

class HierarchyRouter(private var context: FragmentActivity?) {
    fun clickChapter(hierarchy: ParcelChapter) {
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