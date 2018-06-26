package com.example.aamezencev.handbook.presentation.list

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy

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
        fun addHierarchyElement(hierarchyElement: HierarchyElement)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getHierarchy(parentId: Long?)
        fun insertHierarchyElement(hierarchyElement: HierarchyElementDb,
                                   data: DataHierarchyDb,
                                   modelList: List<ThreeDimensionalModelDb>)
    }
}