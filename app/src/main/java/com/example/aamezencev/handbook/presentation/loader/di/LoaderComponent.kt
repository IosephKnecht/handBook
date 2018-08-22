package com.example.aamezencev.handbook.presentation.loader.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import dagger.Subcomponent

@Subcomponent(modules = [LoaderModule::class])
@ModuleScope
interface LoaderComponent {
    fun getViewModel(): LoaderContract.ViewModel
    fun getPresenter(): LoaderContract.Presenter
}