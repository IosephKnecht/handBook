package com.example.aamezencev.handbook.presentation.log.screen.viewModel

import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.presentation.log.screen.LogScreenContract

class LogScreenViewModel : AbstractViewModel(), LogScreenContract.ViewModel {
    override var logItemModel: LogItemModel? = null
        set(value) {
            field = value
            notifyChange()
        }
}