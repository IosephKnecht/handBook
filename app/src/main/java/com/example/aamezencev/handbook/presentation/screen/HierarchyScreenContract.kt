package com.example.aamezencev.handbook.presentation.screen

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

interface HierarchyScreenContract {
    interface ViewModel : MvpViewModel {
        var description: String?
            @Bindable get
        var listModels: List<ThreeDimensionalModel>?
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainDataElement(data: DataHierarchyElement)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainDataElement(dataId: Long)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getDataElement(dataId: Long)
    }

}