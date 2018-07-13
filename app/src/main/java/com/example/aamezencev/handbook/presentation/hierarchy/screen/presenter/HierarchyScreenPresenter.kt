package com.example.aamezencev.handbook.presentation.hierarchy.screen.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.domain.mappers.SpannableMapper
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.screen.router.HierarchyScreenRouter

class HierarchyScreenPresenter(private var router: HierarchyScreenContract.Router,
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

    override fun onObtainDataElement(data: DataElement) {
        viewModel!!.description = SpannableMapper.fromSpannable(data.description, data.pointerList) {
            router.showViewer(androidComponent!!, it.thrModelId)
        }
    }
}