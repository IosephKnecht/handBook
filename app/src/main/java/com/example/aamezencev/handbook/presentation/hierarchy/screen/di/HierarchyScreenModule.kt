package com.example.aamezencev.handbook.presentation.hierarchy.screen.di

import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.screen.interactor.HierarchyScreenInteractor
import com.example.aamezencev.handbook.presentation.hierarchy.screen.presenter.HierarchyScreenPresenter
import com.example.aamezencev.handbook.presentation.hierarchy.screen.router.HierarchyScreenRouter
import com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel.HierarchyInfoVM
import dagger.Module
import dagger.Provides

@Module
class HierarchyScreenModule {
    @Provides
    @ModuleScope
    fun provideHierarchyScreenViewModel(): HierarchyScreenContract.ViewModel {
        return HierarchyInfoVM()
    }

    @Provides
    @ModuleScope
    fun provideHierarchyScreenPresenter(router: HierarchyScreenRouter,
                                        interactor: HierarchyScreenContract.Interactor): HierarchyScreenContract.Presenter {
        return HierarchyScreenPresenter(router, interactor)
    }

    @Provides
    @ModuleScope
    fun provideHierarchyScreenInteractor(dataBaseService: DataBaseService): HierarchyScreenContract.Interactor {
        return HierarchyScreenInteractor(dataBaseService)
    }

    @Provides
    @ModuleScope
    fun provideHierarchyScreenRouter(): HierarchyScreenRouter {
        return HierarchyScreenRouter()
    }
}