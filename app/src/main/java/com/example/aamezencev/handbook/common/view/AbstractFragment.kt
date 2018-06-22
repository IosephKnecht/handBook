package com.example.aamezencev.handbook.common.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.common.helper.AbstractLoader
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractFragment<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : Fragment(), AndroidComponent, LoaderManager.LoaderCallbacks<Presenter> {

    private val LOADER_ID = 2

    override val activityComponent: AppCompatActivity
        get() = activity as AppCompatActivity
    override val fragmentManagerComponent: FragmentManager
        get() = activity?.supportFragmentManager as FragmentManager

    abstract fun injectDi()
    abstract fun createPresenter(): Presenter
    abstract fun createViewModel(): VM

    var viewModel: VM? = null
        protected set
    var presenter: Presenter? = null
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDi()
        val loader = activityComponent
                .supportLoaderManager.getLoader<Presenter>(LOADER_ID)
        if (loader == null) {
            activityComponent.supportLoaderManager.initLoader(LOADER_ID, null, this)
            presenter = createPresenter()
            viewModel = createViewModel()
        } else {
            presenter = (loader as AbstractLoader<VM, Presenter>).savePresenter
            viewModel = presenter!!.viewModel
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (viewModel != null) presenter?.attachView(viewModel!!, this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
        presenter = null
        viewModel = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Presenter> {
        val loader = AbstractLoader(this.context!!, createPresenter())
        return loader
    }

    override fun onLoadFinished(loader: Loader<Presenter>, data: Presenter) {
    }

    override fun onLoaderReset(loader: Loader<Presenter>) {
    }
}