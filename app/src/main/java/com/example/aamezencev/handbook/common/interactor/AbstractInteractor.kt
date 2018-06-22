package com.example.aamezencev.handbook.common.interactor

import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractInteractor<Presenter : MvpPresenter<MvpViewModel>> : MvpInteractor<Presenter> {

}