package com.example.aamezencev.handbook.presentation.log.screen.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.log.screen.LogScreenContract
import com.example.aamezencev.handbook.presentation.log.screen.presenter.LogScreenPresenter
import com.example.aamezencev.handbook.presentation.log.screen.viewModel.LogScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class LogScreenModule {
    @Provides
    @ModuleScope
    fun provideViewModel(): LogScreenContract.ViewModel {
        return LogScreenViewModel()
    }

    @Provides
    @ModuleScope
    fun providePresenter(): LogScreenContract.Presenter{
        return LogScreenPresenter()
    }
}