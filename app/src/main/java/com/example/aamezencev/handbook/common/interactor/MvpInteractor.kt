package com.example.aamezencev.handbook.common.interactor

import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface MvpInteractor<Presenter : MvpPresenter<MvpViewModel>> {
    fun setListener(presenter: Presenter)
    fun onDestroy()
}