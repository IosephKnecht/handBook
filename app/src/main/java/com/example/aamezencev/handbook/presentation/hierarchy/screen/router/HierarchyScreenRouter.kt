package com.example.aamezencev.handbook.presentation.hierarchy.screen.router

import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.view.ViewerFragment

class HierarchyScreenRouter : AbstractRouter<HierarchyScreenContract.RouterListener>(), HierarchyScreenContract.Router {
    override fun showViewer(androidComponent: AndroidComponent, thrModelId: Long) {
        androidComponent.activityComponent
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.hierarchy_container, ViewerFragment.instanceFragment(thrModelId))
                .addToBackStack("viewer")
                .commit()
    }
}