package com.example.aamezencev.handbook.presentation.viewer

import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface ViewerContract {
    interface ViewModel : MvpViewModel {

    }

    interface Presenter : MvpPresenter<ViewModel> {

    }

    interface Listener : MvpInteractor.Listener {

    }

    interface Interactor : MvpInteractor<Listener> {

    }

    interface Router : MvpRouter {

    }
}