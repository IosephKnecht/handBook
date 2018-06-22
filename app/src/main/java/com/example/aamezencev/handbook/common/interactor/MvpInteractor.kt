package com.example.aamezencev.handbook.common.interactor

import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface MvpInteractor<L : MvpInteractor.Listener> {
    interface Listener {

    }

    fun setListener(presenter: L)
    fun onDestroy()
}