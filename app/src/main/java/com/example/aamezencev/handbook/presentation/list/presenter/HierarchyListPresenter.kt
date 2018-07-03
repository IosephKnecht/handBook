package com.example.aamezencev.handbook.presentation.list.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.domain.mappers.HierarchyElementMapper
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.list.interactor.HierarchyListInteractor

class HierarchyListPresenter(private var interactor: HierarchyListInteractor?)
    : AbstractPresenter<HierarchyListContract.ViewModel>(), HierarchyListContract.Presenter, HierarchyListContract.Listener {
    private var cacheHierarchy: IHierarchy? = null

    override fun attachView(viewModel: HierarchyListContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor!!.setListener(this)
    }

    override fun detachView() {
        super.detachView()
        interactor!!.setListener(null)
    }

    override fun destroy() {
        interactor?.onDestroy()
        super.destroy()
    }

    override fun onObtainHieararchy(hierarchy: List<HierarchyElementDb>) {
        val list = hierarchy
                .map { HierarchyElementMapper.fromPresentation(it) }
                .toMutableList()
        viewModel!!.hierarchy = list
    }

    override fun obtainHieararchy(parentId: Long?) {
        if (viewModel!!.hierarchy.isEmpty()) interactor!!.getHierarchy(parentId)
    }

    override fun addHierarchyElement(hierarchyElement: HierarchyElement) {
        val containerDb = HierarchyElementMapper.fromDb(hierarchyElement)
        interactor!!.insertHierarchyElement(containerDb)
    }

    override fun addHierarchyListElement(hierarchyElementList: List<HierarchyElement>) {
        interactor!!.insertHierarchyElementList(hierarchyElementList
                .map { HierarchyElementMapper.fromDb(it) })
    }
}