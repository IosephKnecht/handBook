package com.example.aamezencev.handbook.presentation.loader

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment.HierarchyFragment
import javax.inject.Inject

class LoaderInputModule @Inject constructor() : LoaderContract.InputModule {
    override fun createOpenFileIntent() = Intent(Intent.ACTION_GET_CONTENT).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        type = "*/*"
    }

    override fun createHierarchyFragment(parentId: Long): Fragment {
        return HierarchyFragment.instanceFragment(parentId)
    }
}