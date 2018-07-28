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
    private var cachedListUri: MutableList<DatabaseInfo> = mutableListOf()
    private var lastInitUri: Uri? = null

    init {
        cachedListUri = interactor.getCachedFilePath().toMutableList()
    }

    override fun attachView(viewModel: LoaderContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
        router.setListener(this)
        initCacheFilePath()
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
        if (lastInitUri == null && lastInitUri != uri) {
            obtainFilePath(uri)
        }
        router.showHierarchyFragment(androidComponent!!)
    }

    override fun onCopyDatabase(databaseInfo: DatabaseInfo) {
        lastInitUri = databaseInfo.uri
        if (!cachedListUri.contains(databaseInfo)) {
            cachedListUri.add(databaseInfo)
            interactor.cacheFilePath(databaseInfo)
            viewModel!!.cachedUri(databaseInfo)
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

    private fun isDuplicate(uri: Uri?): Boolean {
        return uri?.run {
            cachedListUri.forEach { if (it.uri == this) return true }
            return false
        } ?: true
    }

    private fun initCacheFilePath() {
        if (cachedListUri.isNotEmpty() && cachedListUri != viewModel!!.databaseList)
            viewModel!!.databaseList = cachedListUri
    }
}