package com.example.aamezencev.handbook.presentation.hierarchy.viewer.di

import android.content.Context
import com.example.aamezencev.a3dviewer.di.DaggerViewerComponent
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.ViewerContract
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.interactor.ViewerInteractor
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.presenter.ViewerPresenter
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.viewModel.ViewerViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewerModule(private val context: Context) {
    @Provides
    @ModuleScope
    fun provideViewModel(): ViewerContract.ViewModel {
        return ViewerViewModel()
    }

    @Provides
    @ModuleScope
    fun provideViewerPresenter(interactor: ViewerContract.Interactor): ViewerContract.Presenter {
        return ViewerPresenter(interactor)
    }

    @Provides
    @ModuleScope
    fun provideViewerInteractor(): ViewerContract.Interactor {
        return ViewerInteractor()
    }

    @Provides
    @ModuleScope
    fun provideViewerLibrary(): com.example.aamezencev.a3dviewer.di.ViewerComponent {
        return DaggerViewerComponent
                .builder()
                .viewerModule(com.example.aamezencev.a3dviewer.di.ViewerModule(context))
                .build()
    }

}