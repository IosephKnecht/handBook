package com.example.aamezencev.handbook.common.view

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractActivity<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : AppCompatActivity(), AndroidComponent {

    override val activityComponent: Activity
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
        presenter = createPresenter()
        viewModel = createViewModel()
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
}