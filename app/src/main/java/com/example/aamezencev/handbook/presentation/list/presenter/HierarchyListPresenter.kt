package com.example.aamezencev.handbook.presentation.list.presenter

import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.IHierarchy
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

    override fun onObtainHieararchy(hierarchy: IHierarchy) {
        cacheHierarchy = hierarchy
        viewModel.name = hierarchy.name
        viewModel.childList = try {
            hierarchy.childList.toMutableList()
        } catch (e: Exception) {
            mutableListOf()
        }
        viewModel.text = try {
            hierarchy.text
        } catch (e: Exception) {
            ""
        }
    }

    override fun obtainHieararchy() {
        interactor.getHierarchy()
    }
}