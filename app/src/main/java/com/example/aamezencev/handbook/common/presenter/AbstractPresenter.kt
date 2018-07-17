package com.example.aamezencev.handbook.common.presenter

import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractPresenter<VM : MvpViewModel> : MvpPresenter<VM> {
    final override var viewModel: VM? = null
    final override var androidComponent: AndroidComponent? = null

    override fun attachView(viewModel: VM, androidComponent: AndroidComponent) {
        this.viewModel = viewModel
        this.androidComponent = androidComponent
    }

    override fun detachView() {
        androidComponent = null
    }

    override fun destroy() {
    }
}