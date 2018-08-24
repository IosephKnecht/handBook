package com.example.aamezencev.handbook.presentation.log.list.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import com.hypertrack.hyperlog.DeviceLogModel

class LogListPresenter(private val interactor: LogListContract.Interactor,
                       private val router: LogListContract.Router) : AbstractPresenter<LogListContract.ViewModel>(),
    LogListContract.Presenter,
    LogListContract.Listener,
    LogListContract.RouterListener {

    override fun attachView(viewModel: LogListContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
    }

    override fun detachView() {
        interactor.setListener(null)
        androidComponent = null
        super.detachView()
    }

    override fun obtainLog() {
        interactor.getLogs()
    }

    override fun onObtainLog(logList: List<LogItemModel>) {
        viewModel!!.logList = logList
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }
}