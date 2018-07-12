package com.example.aamezencev.handbook.presentation.hierarchy.screen.router

import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.view.ViewerFragment

class HierarchyScreenRouter : AbstractRouter(), HierarchyScreenContract.Router {
    override fun showViewer(androidComponent: AndroidComponent, model: ThreeDimensionalModel) {
        androidComponent.activityComponent
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.hierarchyContainer, ViewerFragment.instanceFragment(model.modelArray))
                .addToBackStack("viewer")
                .commit()
    }
}