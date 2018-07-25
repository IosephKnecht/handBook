package com.example.aamezencev.handbook.presentation.loader.viewModel

import android.net.Uri
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.presentation.loader.LoaderContract

class LoaderViewModel : AbstractViewModel(), LoaderContract.ViewModel {
    override var uri: Uri? = null
        set(value) {
            field = value
            if (uri != null) state = LoaderContract.State.PARSED
        }
    override var state: LoaderContract.State = LoaderContract.State.IDLE
}