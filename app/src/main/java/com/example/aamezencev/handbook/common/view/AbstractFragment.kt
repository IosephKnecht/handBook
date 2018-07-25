package com.example.aamezencev.handbook.common.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.common.helper.AbstractLoader
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import java.util.*

abstract class AbstractFragment<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : Fragment(), AndroidComponent, LoaderManager.LoaderCallbacks<Presenter> {

    private lateinit var LOADER_ID: UUID

    final override val activityComponent: AppCompatActivity
        get() = activity as AppCompatActivity
    final override val fragmentManagerComponent: FragmentManager
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
        if (savedInstanceState == null) {
            LOADER_ID = UUID.randomUUID()
        } else {
            LOADER_ID = UUID.fromString(savedInstanceState.getString("RANDOM_UUID"))
        }
        injectDi()
        val loader = activityComponent
                .supportLoaderManager.getLoader<Presenter>(LOADER_ID.hashCode())
        if (loader == null) {
            activityComponent.supportLoaderManager.initLoader(LOADER_ID.hashCode(), null, this)
            presenter = createPresenter()
            viewModel = createViewModel()
        } else {
            presenter = (loader as AbstractLoader<VM, Presenter>).savePresenter
            viewModel = presenter!!.viewModel
        }
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachView(viewModel!!, this)
    }

    override fun onStop() {
        presenter!!.detachView()
        super.onStop()
    }

    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//        presenter!!.attachView(viewModel!!,this)
//        return null
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        presenter?.detachView()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("RANDOM_UUID", LOADER_ID.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        if (!activity?.isChangingConfigurations!!) presenter?.destroy()
        super.onDestroy()
    }

    final override fun onCreateLoader(id: Int, args: Bundle?): Loader<Presenter> {
        val loader = AbstractLoader(this.context!!, createPresenter())
        return loader
    }

    final override fun onLoadFinished(loader: Loader<Presenter>, data: Presenter) {
    }

    final override fun onLoaderReset(loader: Loader<Presenter>) {
    }
}