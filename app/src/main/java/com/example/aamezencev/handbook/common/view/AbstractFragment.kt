package com.example.aamezencev.handbook.common.view

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractFragment<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : Fragment(), AndroidComponent {

    override val activityComponent: Activity
        get() = activity as Activity
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
        presenter = createPresenter()
        viewModel = createViewModel()
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
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
}