package com.example.aamezencev.handbook.common.interactor

import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {
    var interactorListener: L? = null

    override fun onDestroy() {
        interactorListener = null
    }
}