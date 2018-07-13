package com.example.aamezencev.handbook.presentation.hierarchy.screen

import android.content.Intent
import android.databinding.Bindable
import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

interface HierarchyScreenContract {
    interface ViewModel : MvpViewModel {
        var description: SpannableStringBuilder
            @Bindable get
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainDataElement(data: DataElement)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainDataElement(dataId: Long)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getDataElement(dataId: Long)
    }

    interface Router {
        fun showViewer(androidComponent: AndroidComponent, thrModelId: Long)
    }

}