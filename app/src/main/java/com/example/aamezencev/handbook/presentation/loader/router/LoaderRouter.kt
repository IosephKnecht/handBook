package com.example.aamezencev.handbook.presentation.loader.router

import android.net.Uri
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import kotlinx.android.synthetic.main.activity_main.view.*

class LoaderRouter(private val inputModule: LoaderInputModule) : AbstractRouter<LoaderContract.RouterListener>(), LoaderContract.Router {
    override fun convertUri(androidComponent: AndroidComponent, uri: Uri?) {
        val inputStream = androidComponent.activityComponent.contentResolver.openInputStream(uri)
        routerListener!!.onCovertUri(uri, inputStream)
    }

    override fun showSelectFileActivity(androidComponent: AndroidComponent) {
        androidComponent.activityComponent.startActivityForResult(
                inputModule.createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
    }

    override fun showHierarchyFragment(androidComponent: AndroidComponent) {
        androidComponent.fragmentManagerComponent.beginTransaction()
                .replace(R.id.hierarchyContainer, inputModule.createHierarchyFragment(-1))
                .addToBackStack(null)
                .commit()
    }
}