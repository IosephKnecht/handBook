package com.example.aamezencev.handbook.presentation.log.list.viewModel

import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import com.hypertrack.hyperlog.DeviceLogModel

class LogListViewModel : AbstractViewModel(), LogListContract.ViewModel {
    override var logList: List<LogItemModel> = listOf()
        set(value) {
            field = value
            notifyChange()
        }
}