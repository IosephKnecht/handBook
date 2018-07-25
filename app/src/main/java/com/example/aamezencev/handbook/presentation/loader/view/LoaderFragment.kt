package com.example.aamezencev.handbook.presentation.loader.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import com.example.aamezencev.handbook.presentation.loader.di.LoaderComponent
import com.example.aamezencev.handbook.presentation.loader.di.LoaderModule
import kotlinx.android.synthetic.main.loader_fragment.*

class LoaderFragment : AbstractFragment<LoaderContract.ViewModel, LoaderContract.Presenter>() {
    private var diComponent: LoaderComponent? = null

    companion object {
        fun instanceFragment() = LoaderFragment()
    }

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.loader_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val module = LoaderInputModule()
        when (viewModel!!.state) {
            LoaderContract.State.IDLE -> {
                fab_database.setOnClickListener {
                    startActivityForResult(module.createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
                }
            }
            LoaderContract.State.PARSED -> {
                presenter!!.obtainFilePath(viewModel!!.uri)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DATABASE_READ_REQUEST_CODE) {
            viewModel!!.uri = data?.data
        }
    }
}