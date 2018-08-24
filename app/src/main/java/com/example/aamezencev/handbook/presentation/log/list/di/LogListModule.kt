package com.example.aamezencev.handbook.presentation.log.list.di

import com.example.aamezencev.handbook.domain.services.FileManagerService
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import com.example.aamezencev.handbook.presentation.log.list.interactor.LogListInteractor
import com.example.aamezencev.handbook.presentation.log.list.presenter.LogListPresenter
import com.example.aamezencev.handbook.presentation.log.list.router.LogListRouter
import com.example.aamezencev.handbook.presentation.log.list.viewModel.LogListViewModel
import dagger.Module
import dagger.Provides

@Module
class LogListModule {
    @Provides
    @ModuleScope
    fun provideViewModel(): LogListContract.ViewModel {
        return LogListViewModel()
    }

    @Provides
    @ModuleScope
    fun providePresenter(interactor: LogListContract.Interactor,
                         router: LogListContract.Router): LogListContract.Presenter {
        return LogListPresenter(interactor, router)
    }

    @Provides
    @ModuleScope
    fun provideInteractor(fileManagerService: FileManagerService): LogListContract.Interactor {
        return LogListInteractor(fileManagerService)
    }

    @Provides
    @ModuleScope
    fun provideRouter(): LogListContract.Router {
        return LogListRouter()
    }
}