package com.example.aamezencev.handbook.presentation.loader.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import java.io.InputStream

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService) :
        AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {
    override fun copyDatabase(inputSteam: InputStream?) {
        discardResult(databaseLoaderService.copyDatabase(inputSteam)){listener, result ->
            listener!!.onCopyDatabase(true)
        }
    }
}