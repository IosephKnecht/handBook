package com.example.aamezencev.handbook.presentation.screen.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.screen.router.HierarchyScreenRouter

class HierarchyScreenPresenter(private var router: HierarchyScreenRouter,
                               private var interactor: HierarchyScreenContract.Interactor) : AbstractPresenter<HierarchyScreenContract.ViewModel>(),
        HierarchyScreenContract.Presenter, HierarchyScreenContract.Listener {
    override fun attachView(viewModel: HierarchyScreenContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
    }

    override fun detachView() {
        super.detachView()
        androidComponent = null
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }

    override fun obtainDataElement(dataId: Long) {
        interactor.getDataElement(dataId)
    }

    override fun onObtainDataElement(data: DataHierarchyElement) {
        viewModel!!.description = data.description
        viewModel!!.listModels = data.threeDimensionalModels
    }
}