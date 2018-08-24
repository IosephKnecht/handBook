package com.example.aamezencev.handbook.presentation.hierarchy.list

import android.databinding.Bindable
import android.support.v4.app.Fragment
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement

interface HierarchyListContract {
    interface ViewModel : MvpViewModel {
        var hierarchy: MutableList<HierarchyElement>
            @Bindable get
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainHieararchy(hierarchy: List<HierarchyElementDb>)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainHieararchy(parentId: Long?)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getHierarchy(parentId: Long?)
    }

    interface InputModule {
        fun createChapter(parentId: Long): Fragment
        fun createPage(dataId: Long, position: Long = 0): Fragment
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun showChapter(androidComponent: AndroidComponent, parentId: Long)
        fun showPage(androidComponent: AndroidComponent, dataId: Long)
    }
}