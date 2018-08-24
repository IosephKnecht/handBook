package com.example.aamezencev.handbook.presentation.log.screen

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.LogItemModel

interface LogScreenContract {
    interface ViewModel : MvpViewModel {
        var logItemModel: LogItemModel?
            @Bindable get
    }

    interface Presenter : MvpPresenter<ViewModel>

    interface Listener : MvpInteractor.Listener

    interface Interactor : MvpInteractor<Listener>

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener>
}