package com.example.aamezencev.handbook.presentation.hierarchy.viewer

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

interface ViewerContract {
    interface ViewModel : MvpViewModel {
        var thrModel: ThreeDimensionalModel?
            @Bindable get
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainThrModel(thrModelId: Long)
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainThrModel(model: ThreeDimensionalModelDb)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getThreeDimensionalModel(thrModelId: Long)
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {

    }
}