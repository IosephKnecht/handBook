package com.example.aamezencev.handbook.presentation.hierarchy.list

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
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
}