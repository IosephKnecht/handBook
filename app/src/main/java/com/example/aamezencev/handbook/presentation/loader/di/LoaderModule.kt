package com.example.aamezencev.handbook.presentation.loader.di

import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import com.example.aamezencev.handbook.presentation.loader.interactor.LoaderInteractor
import com.example.aamezencev.handbook.presentation.loader.presenter.LoaderPresenter
import com.example.aamezencev.handbook.presentation.loader.router.LoaderRouter
import com.example.aamezencev.handbook.presentation.loader.viewModel.LoaderViewModel
import dagger.Module
import dagger.Provides

@Module
class LoaderModule {
    @Provides
    @ModuleScope
    fun provideViewModel(): LoaderContract.ViewModel {
        return LoaderViewModel()
    }

    @Provides
    @ModuleScope
    fun providePresenter(interactor: LoaderContract.Interactor, router: LoaderContract.Router): LoaderContract.Presenter {
        return LoaderPresenter(interactor, router)
    }

    @Provides
    @ModuleScope
    fun provideInteractor(databaseLoaderService: DatabaseLoaderService): LoaderContract.Interactor {
        return LoaderInteractor(databaseLoaderService)
    }

    @Provides
    @ModuleScope
    fun provideRouter(inputModule: LoaderInputModule): LoaderContract.Router {
        return LoaderRouter(inputModule)
    }
}