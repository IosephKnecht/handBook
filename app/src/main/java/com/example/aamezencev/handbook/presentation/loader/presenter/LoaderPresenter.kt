package com.example.aamezencev.handbook.presentation.loader.presenter

import android.net.Uri
import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import java.io.InputStream

class LoaderPresenter(private val interactor: LoaderContract.Interactor,
                      private val router: LoaderContract.Router) :
        AbstractPresenter<LoaderContract.ViewModel>(), LoaderContract.Presenter, LoaderContract.Listener,
        LoaderContract.RouterListener {
    private var lastUri: Uri? = null

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
        interactor.copyDatabase(viewModel!!.loadableUri!!, inputSteam)
    }

    override fun obtainFilePath(uri: Uri?) {
        //condition on double loading
        if (lastUri == null || lastUri != uri) {
            lastUri = uri!!
            router.convertUri(androidComponent!!, uri)
        }
    }

    override fun openHierarchyFragment(uri: Uri) {
        obtainFilePath(uri)
        router.showHierarchyFragment(androidComponent!!)
    }

    override fun onCopyDatabase(databaseInfo: DatabaseInfo) {
        viewModel!!.cachedUri(databaseInfo)
        //router.showHierarchyFragment(androidComponent!!)
    }

    override fun invalidateCache() {
        lastUri = null
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }
}