package com.example.aamezencev.handbook.presentation.log.screen.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.log.screen.LogScreenContract
import dagger.Subcomponent

@Subcomponent(modules = [LogScreenModule::class])
@ModuleScope
interface LogScreenComponent {
    fun getViewModel(): LogScreenContract.ViewModel
    fun getPresenter(): LogScreenContract.Presenter
}