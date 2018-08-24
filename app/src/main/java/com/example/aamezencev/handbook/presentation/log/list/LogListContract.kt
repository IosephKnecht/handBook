package com.example.aamezencev.handbook.presentation.log.list

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.hypertrack.hyperlog.DeviceLogModel

interface LogListContract {
    interface ViewModel : MvpViewModel {
        var logList: List<LogItemModel>
            @Bindable get
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainLog()
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainLog(logList: List<LogItemModel>)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getLogs()
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener>
}