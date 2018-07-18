package com.example.aamezencev.handbook.presentation.loader.presenter

import android.net.Uri
import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import java.io.InputStream

class LoaderPresenter(private val interactor: LoaderContract.Interactor,
                      private val router: LoaderContract.Router) :
        AbstractPresenter<LoaderContract.ViewModel>(), LoaderContract.Presenter, LoaderContract.Listener,
        LoaderContract.RouterListener {

    override fun attachView(viewModel: LoaderContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
        router.setListener(this)
    }

    override fun detachView() {
        interactor.setListener(null)
        router.setListener(null)
        super.detachView()
    }

    override fun onCovertUri(inputSteam: InputStream?) {
        interactor.copyDatabase(inputSteam)
    }

    override fun obtainFilePath(uri: Uri?) {
        router.convertUri(androidComponent!!, uri)
    }

    override fun onCopyDatabase(valid: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }
}