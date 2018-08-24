package com.example.aamezencev.handbook.presentation.log.screen.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.presentation.log.screen.LogScreenContract

class LogScreenPresenter : AbstractPresenter<LogScreenContract.ViewModel>(), LogScreenContract.Presenter,
    LogScreenContract.Listener,
    LogScreenContract.RouterListener {
}