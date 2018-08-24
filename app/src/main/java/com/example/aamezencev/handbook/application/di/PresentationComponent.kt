package com.example.aamezencev.handbook.application.di

import com.example.aamezencev.handbook.presentation.PresentationScope
import com.example.aamezencev.handbook.presentation.bookmarks.di.BookmarksComponent
import com.example.aamezencev.handbook.presentation.bookmarks.di.BookmarksModule
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListComponent
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListModule
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenComponent
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenModule
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerComponent
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerModule
import com.example.aamezencev.handbook.presentation.loader.di.LoaderComponent
import com.example.aamezencev.handbook.presentation.loader.di.LoaderModule
import com.example.aamezencev.handbook.presentation.log.list.di.LogListComponent
import com.example.aamezencev.handbook.presentation.log.list.di.LogListModule
import com.example.aamezencev.handbook.presentation.log.screen.di.LogScreenComponent
import com.example.aamezencev.handbook.presentation.log.screen.di.LogScreenModule
import dagger.Component

@Component(modules = [PresentationModule::class], dependencies = [BusinessComponent::class])
@PresentationScope
interface PresentationComponent {
    fun addHierarchyListSubmodule(module: HierarchyListModule): HierarchyListComponent
    fun addHierarchyScreenSubmodule(module: HierarchyScreenModule): HierarchyScreenComponent
    fun addViewerModule(viewerModule: ViewerModule): ViewerComponent
    fun addLoaderSubmodule(loaderModule: LoaderModule): LoaderComponent
    fun addBookmarksModule(bookmarksModule: BookmarksModule): BookmarksComponent
    fun addLogListSubmodule(logListModule: LogListModule): LogListComponent
    fun addLogScreenSubmodule(logScreenSubmodule: LogScreenModule): LogScreenComponent
}