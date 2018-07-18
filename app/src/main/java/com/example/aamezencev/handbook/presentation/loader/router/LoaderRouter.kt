package com.example.aamezencev.handbook.presentation.loader.router

import android.net.Uri
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.router.AbstractRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule

class LoaderRouter(private val inputModule: LoaderInputModule) : AbstractRouter<LoaderContract.RouterListener>(), LoaderContract.Router {
    override fun convertUri(androidComponent: AndroidComponent, uri: Uri?) {
        val inputStream = androidComponent.activityComponent.contentResolver.openInputStream(uri)
        routerListener!!.onCovertUri(inputStream)
    }

    override fun showSelectFileActivity(androidComponent: AndroidComponent) {
        androidComponent.activityComponent.startActivityForResult(
                inputModule.createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
    }
}