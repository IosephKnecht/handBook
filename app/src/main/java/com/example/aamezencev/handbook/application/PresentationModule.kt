package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.presentation.PresentationScope
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListInputModule
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    @PresentationScope
    fun provideHierarchyListInputModule(): HierarchyListContract.InputModule {
        return HierarchyListInputModule()
    }
}