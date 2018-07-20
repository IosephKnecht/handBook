package com.example.aamezencev.handbook.application.di

import com.example.aamezencev.handbook.presentation.PresentationScope
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListInputModule
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    @PresentationScope
    fun provideHierarchyListInputModule(): HierarchyListContract.InputModule {
        return HierarchyListInputModule()
    }

    @Provides
    @PresentationScope
    fun provideLoaderInputModule(): LoaderContract.InputModule {
        return LoaderInputModule()
    }
}