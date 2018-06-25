package com.example.aamezencev.handbook.presentation.list.presenter

import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.domain.HierarchyElementMapper
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.list.interactor.HierarchyListInteractor

class HierarchyListPresenter(override val viewModel: HierarchyListContract.ViewModel,
                             val interactor: HierarchyListInteractor)
    : HierarchyListContract.Presenter, HierarchyListContract.Listener {
    private var androidComponent: AndroidComponent? = null
    //private val interactor: HierarchyListContract.Interactor
    private var cacheHierarchy: IHierarchy? = null

//    //dependency
//    init {
//        interactor = HierarchyListInteractor()
//        interactor.setListener(this)
//    }

    override fun attachView(viewModel: HierarchyListContract.ViewModel, androidComponent: AndroidComponent) {
        this.androidComponent = androidComponent
        interactor.setListener(this)
    }

    override fun detachView() {
        this.androidComponent = null
        interactor.setListener(null)
    }

    override fun destroy() {

    }

    override fun onObtainHieararchy(hierarchy: List<HierarchyElementDb>) {
        viewModel.hierarchy = hierarchy
                .map { HierarchyElementMapper.fromPresentation(it) }
                .toMutableList()
    }

    override fun obtainHieararchy(parentId: Long?) {
        interactor.getHierarchy(parentId)
    }
}