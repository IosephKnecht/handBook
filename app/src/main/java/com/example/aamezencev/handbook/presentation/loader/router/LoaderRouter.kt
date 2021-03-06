package com.example.aamezencev.handbook.presentation.loader.router

import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule

class LoaderRouter(private val inputModule: LoaderInputModule) : AbstractRouter<LoaderContract.RouterListener>(), LoaderContract.Router {
    override fun showSelectFileActivity(androidComponent: AndroidComponent) {
        androidComponent.activityComponent.startActivityForResult(
            inputModule.createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
    }

    override fun showHierarchyFragment(androidComponent: AndroidComponent) {
        androidComponent.fragmentManagerComponent.beginTransaction()
            .replace(R.id.hierarchy_container, inputModule.createHierarchyFragment(-1))
            .addToBackStack(null)
            .commit()
    }

    override fun showBookmarksFragment(androidComponent: AndroidComponent) {
        androidComponent.fragmentManagerComponent.beginTransaction()
            .replace(R.id.hierarchy_container, inputModule.createBookmarkFragment())
            .addToBackStack(null)
            .commit()
    }
}