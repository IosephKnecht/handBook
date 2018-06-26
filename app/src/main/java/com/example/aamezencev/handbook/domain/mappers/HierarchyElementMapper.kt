package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement

object HierarchyElementMapper {
    fun fromPresentation(model: HierarchyElementDb): HierarchyElement {
        return HierarchyElement(model.primaryKey,
                model.parentId,
                model.name,
                model.dataHierarchyId,
                DataHierarchyElementMapper.fromPresentation(model.dataHierarchyDb))
    }

    fun fromDb(model: HierarchyElement): Triple<HierarchyElementDb, DataHierarchyDb, List<ThreeDimensionalModelDb>?> {
        return Triple(HierarchyElementDb()
                .apply {
                    name = model.name
                    parentId = null
                },
                DataHierarchyElementMapper.fromDb(model.dataHierarchyElement),
                ThreeDimensionalMapper.fromDb(model.dataHierarchyElement?.threeDimensionalModels))
    }

//    fun fromViewModel(model: HierarchyElement): HierarchyElementVM {
//        return HierarchyElementVM().apply {
//            hierarchy=HierarchyElement(model.)
//        }
//    }
}