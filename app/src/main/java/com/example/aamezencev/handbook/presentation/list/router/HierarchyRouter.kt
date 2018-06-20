package com.example.aamezencev.handbook.presentation.list.router

import android.support.v4.app.FragmentActivity
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.parcel.ParcelChapter
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment

class HierarchyRouter(private var context: FragmentActivity?) {
    fun clickChapter(hierarchy: ParcelChapter) {
        context?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(hierarchy))
                ?.commit()
    }
}