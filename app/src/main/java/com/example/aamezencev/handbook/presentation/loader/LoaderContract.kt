package com.example.aamezencev.handbook.presentation.loader

import android.content.Intent
import android.net.Uri
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import java.io.InputStream

interface LoaderContract {
    interface ViewModel : MvpViewModel

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainFilePath(uri: Uri?)
    }

    interface Listener : MvpInteractor.Listener {
        fun onCopyDatabase(valid: Boolean)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun copyDatabase(inputSteam: InputStream?)
    }

    interface RouterListener : MvpRouter.Listener {
        fun onCovertUri(inputSteam: InputStream?)
    }

    interface Router : MvpRouter<RouterListener> {
        fun convertUri(androidComponent: AndroidComponent, uri: Uri?)
        fun showSelectFileActivity(androidComponent: AndroidComponent)
    }

    interface InputModule {
        fun createOpenFileIntent(): Intent
    }
}