package com.example.aamezencev.handbook.presentation.screen.view.adapter

import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.aamezencev.handbook.presentation.screen.view.PagerFragment
import com.example.aamezencev.handbook.presentation.screen.viewModel.HierarchyInfoVM

class PagerAdapter(fragmentManager: FragmentManager?, private val viewModel: Parcelable) : FragmentStatePagerAdapter(fragmentManager) {
    private var cacheValue: Int? = null
    override fun getItem(position: Int): Fragment {
        return PagerFragment.instanceFragment(position, viewModel)
    }

    override fun getCount(): Int {
        val pageCount = (viewModel as HierarchyInfoVM).description?.run { length % 1000 } ?: 1
        if (cacheValue != pageCount) {
            cacheValue = pageCount
            notifyDataSetChanged()
            return pageCount
        } else {
            return pageCount
        }
    }

}