package com.example.aamezencev.handbook.common.view

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.common.helper.AbstractLoader
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractActivity<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : AppCompatActivity(), AndroidComponent, LoaderManager.LoaderCallbacks<Presenter> {

    private val LOADER_ID = 1

    override val activityComponent: AppCompatActivity
        get() = this
    override val fragmentManagerComponent: FragmentManager
        get() = supportFragmentManager

    var presenter: Presenter? = null
        protected set
    var viewModel: VM? = null
        protected set

    abstract fun injectDi()
    abstract fun createPresenter(): Presenter
    abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDi()
        val loader = supportLoaderManager
                .getLoader<AbstractLoader<VM, Presenter>>(LOADER_ID) as AbstractLoader<VM, Presenter>?
        if (loader == null) {
            supportLoaderManager.initLoader(LOADER_ID, null, this)
            presenter = createPresenter()
            viewModel = createViewModel()
        } else {
            presenter = loader.savePresenter
            viewModel = presenter!!.viewModel
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel != null)
            presenter?.attachView(viewModel!!, this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
        presenter = null
        viewModel = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Presenter> {
        val loader = AbstractLoader(this, createPresenter())
        return loader
    }

    override fun onLoadFinished(loader: Loader<Presenter>, data: Presenter) {
    }

    override fun onLoaderReset(loader: Loader<Presenter>) {
    }
}