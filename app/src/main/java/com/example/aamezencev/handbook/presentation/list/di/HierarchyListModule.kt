package com.example.aamezencev.handbook.presentation.list.di

import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.list.interactor.HierarchyListInteractor
import com.example.aamezencev.handbook.presentation.list.presenter.HierarchyListPresenter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM
import dagger.Module
import dagger.Provides

@Module
class HierarchyListModule {

    @Provides
    @ModuleScope
    fun provideHierarchyListPresenter(viewModel: HierarchyElementVM, interactor: HierarchyListInteractor): HierarchyListPresenter {
        return HierarchyListPresenter(interactor)
    }

    @Provides
    @ModuleScope
    fun provideHierarchyListViewModel(): HierarchyElementVM {
        return HierarchyElementVM()
    }

    @Provides
    @ModuleScope
    fun provideHierarchyListInteractor(dataBaseService: DataBaseService): HierarchyListInteractor {
        return HierarchyListInteractor(dataBaseService)
    }
}