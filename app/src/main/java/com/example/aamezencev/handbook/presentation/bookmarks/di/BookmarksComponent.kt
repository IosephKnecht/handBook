package com.example.aamezencev.handbook.presentation.bookmarks.di

import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import com.example.aamezencev.handbook.presentation.common.ModuleScope
import dagger.Subcomponent

@Subcomponent(modules = [BookmarksModule::class])
@ModuleScope
interface BookmarksComponent {
    fun getViewModel(): BookmarksContract.ViewModel
    fun getPresenter(): BookmarksContract.Presenter
    fun getRouter(): BookmarksContract.Router
}