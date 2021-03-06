package com.example.aamezencev.handbook.presentation.loader.presenter

import android.net.Uri
import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.loader.LoaderContract

class LoaderPresenter(private val interactor: LoaderContract.Interactor,
                      private val router: LoaderContract.Router) :
    AbstractPresenter<LoaderContract.ViewModel>(), LoaderContract.Presenter, LoaderContract.Listener,
    LoaderContract.RouterListener {
    override fun attachView(viewModel: LoaderContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
        router.setListener(this)

        updateViewModelList()
    }

    override fun detachView() {
        interactor.setListener(null)
        router.setListener(null)
        super.detachView()
    }

    override fun obtainFilePath(uri: Uri?) {
        if (uri != null) interactor.copyDatabase(uri)
    }

    override fun deleteFilePath(databaseInfo: DatabaseInfo) {
        interactor.removeFilepath(databaseInfo)
    }

    override fun onObtainFilePath(databaseInfo: DatabaseInfo) {
        viewModel!!.cachedUri(databaseInfo)
        if (viewModel!!.state == LoaderContract.State.OPEN)
            router.showHierarchyFragment(androidComponent!!)
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }

    private fun updateViewModelList() {
        val cachedListUri = interactor.getCachedFilePath()
        if (viewModel!!.databaseList != cachedListUri)
            viewModel!!.databaseList = cachedListUri.toMutableList()
    }
}