package com.example.aamezencev.handbook.presentation.loader.presenter

import android.net.Uri
import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import java.io.InputStream

class LoaderPresenter(private val interactor: LoaderContract.Interactor,
                      private val router: LoaderContract.Router) :
        AbstractPresenter<LoaderContract.ViewModel>(), LoaderContract.Presenter, LoaderContract.Listener,
        LoaderContract.RouterListener {
    private var lastInitUri: Uri? = null

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

    override fun onCovertUri(uri: Uri?, inputSteam: InputStream?) {
        interactor.copyDatabase(uri!!, inputSteam)
    }

    override fun obtainFilePath(uri: Uri?) {
        //condition on double loading
        if (lastInitUri == null || lastInitUri != uri) {
            router.convertUri(androidComponent!!, uri)
        }
    }

    override fun openHierarchyFragment(uri: Uri) {
        obtainFilePath(uri)
        router.showHierarchyFragment(androidComponent!!)
    }

    override fun onCopyDatabase(databaseInfo: DatabaseInfo) {
        if (viewModel!!.cachedUri(databaseInfo)) {
            lastInitUri = databaseInfo.uri
        }
        //router.showHierarchyFragment(androidComponent!!)
    }

    override fun invalidateCache() {
        lastInitUri = null
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