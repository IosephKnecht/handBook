package com.example.aamezencev.handbook.presentation.loader.viewModel

import android.databinding.Bindable
import android.net.Uri
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.presentation.loader.LoaderContract

class LoaderViewModel : AbstractViewModel(), LoaderContract.ViewModel {
    override var loadableUri: Uri? = null
    override var databaseList: MutableList<DatabaseInfo> = mutableListOf()
        @Bindable get() = field
        set(value) {
            field = value
            notifyChange()
        }

    override fun cachedUri(databaseInfo: DatabaseInfo) {
        if (databaseList.find { it.uri == databaseInfo.uri } == null) {
            databaseList.add(databaseInfo)
            notifyChange()
        }
        loadableUri = null
    }
}