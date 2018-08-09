package com.example.aamezencev.handbook.presentation.bookmarks.di

import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import com.example.aamezencev.handbook.presentation.bookmarks.interactor.BookmarksInteractor
import com.example.aamezencev.handbook.presentation.bookmarks.presenter.BookmarksPresenter
import com.example.aamezencev.handbook.presentation.bookmarks.router.BookmarksRouter
import com.example.aamezencev.handbook.presentation.bookmarks.viewModel.BookmarkViewModel
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListInputModule
import dagger.Module
import dagger.Provides

@Module
class BookmarksModule {
    @Provides
    @ModuleScope
    fun provideViewModel(): BookmarksContract.ViewModel {
        return BookmarkViewModel()
    }

    @Provides
    @ModuleScope
    fun providePresenter(interactor: BookmarksContract.Interactor,
                         router: BookmarksContract.Router): BookmarksContract.Presenter {
        return BookmarksPresenter(interactor, router)
    }

    @Provides
    @ModuleScope
    fun provideInteractor(sharedPreferenceService: SharedPreferenceService): BookmarksContract.Interactor {
        return BookmarksInteractor(sharedPreferenceService)
    }

    @Provides
    @ModuleScope
    fun provideRouter(module: HierarchyListContract.InputModule): BookmarksContract.Router {
        return BookmarksRouter(module)
    }
}