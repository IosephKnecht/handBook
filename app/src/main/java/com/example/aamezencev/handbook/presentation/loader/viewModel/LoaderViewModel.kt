package com.example.aamezencev.handbook.presentation.loader.viewModel

import android.databinding.Bindable
import android.net.Uri
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.loader.LoaderContract

class LoaderViewModel : AbstractViewModel(), LoaderContract.ViewModel {
    override var state: LoaderContract.State = LoaderContract.State.LOAD
    override var loadableUri: Uri? = null
    override var databaseList: MutableList<DatabaseInfo> = mutableListOf()
        @Bindable get() = field
        set(value) {
            field = value
            notifyChange()
        }

    override fun cachedUri(databaseInfo: DatabaseInfo): Boolean {
        val isUnique = databaseList.find { it.uri == databaseInfo.uri } == null
        if (isUnique) {
            databaseList.add(databaseInfo)
            notifyChange()
        }
        loadableUri = null
        return isUnique
    }
}