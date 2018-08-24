package com.example.aamezencev.handbook.presentation.log.list.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import dagger.Subcomponent

@Subcomponent(modules = [LogListModule::class])
@ModuleScope
interface LogListComponent {
    fun getViewModel(): LogListContract.ViewModel
    fun getPresenter(): LogListContract.Presenter
}