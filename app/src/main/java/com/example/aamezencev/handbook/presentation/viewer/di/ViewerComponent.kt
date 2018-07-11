package com.example.aamezencev.handbook.presentation.viewer.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.viewer.ViewerContract
import dagger.Subcomponent

@Subcomponent(modules = [ViewerModule::class])
@ModuleScope
interface ViewerComponent {
    fun getPresenter(): ViewerContract.Presenter
    fun getViewModel(): ViewerContract.ViewModel

    fun getViewer(): com.example.aamezencev.a3dviewer.di.ViewerComponent
}