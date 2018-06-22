package com.example.aamezencev.handbook.presentation.list.presenter

import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.list.HierarchyContract

class HierarchyListPresenter(override val viewModel: HierarchyContract.ViewModel) : HierarchyContract.Presenter {
    private var androidComponent: AndroidComponent? = null

    override fun attachView(viewModel: HierarchyContract.ViewModel, androidComponent: AndroidComponent) {
        this.androidComponent = androidComponent
    }

    override fun detachView() {
        this.androidComponent = null
    }

    override fun destroy() {

    }
}