package com.example.aamezencev.handbook.presentation.loader.view

import android.content.Intent
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import com.example.aamezencev.handbook.presentation.loader.di.LoaderComponent
import com.example.aamezencev.handbook.presentation.loader.di.LoaderModule

class LoaderFragment : AbstractFragment<LoaderContract.ViewModel, LoaderContract.Presenter>() {
    private var diComponent: LoaderComponent? = null

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
                .addLoaderSubmodule(LoaderModule())
    }

    override fun createPresenter(): LoaderContract.Presenter {
        return diComponent!!.getPresenter()
    }

    override fun createViewModel(): LoaderContract.ViewModel {
        return diComponent!!.getViewModel()
    }

    override fun onStart() {
        super.onStart()
        val module = LoaderInputModule()
        startActivityForResult(module.createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DATABASE_READ_REQUEST_CODE) {
            presenter!!.obtainFilePath(data?.data)
        }
    }
}