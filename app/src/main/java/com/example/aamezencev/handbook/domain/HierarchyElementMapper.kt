package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM

object HierarchyElementMapper {
    fun fromPresentation(model: HierarchyElementDb): HierarchyElement {
        return HierarchyElement(model.primaryKey,
                model.parentId,
                model.name,
                model.dataHierarchyId,
                DataHierarchyElementMapper.fromPresentation(model.dataHierarchyDb))
    }

//    fun fromViewModel(model: HierarchyElement): HierarchyElementVM {
//        return HierarchyElementVM().apply {
//            hierarchy=HierarchyElement(model.)
//        }
//    }
}