package com.example.aamezencev.handbook.presentation.hierarchy.viewer.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.ViewerContract

class ViewerPresenter(private val interactor: ViewerContract.Interactor) :
        AbstractPresenter<ViewerContract.ViewModel>(), ViewerContract.Presenter, ViewerContract.Listener {
    override fun attachView(viewModel: ViewerContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
    }

    override fun detachView() {
        androidComponent = null
        super.detachView()
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }
}