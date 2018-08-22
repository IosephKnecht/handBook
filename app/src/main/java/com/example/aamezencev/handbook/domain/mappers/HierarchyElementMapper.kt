package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement

object HierarchyElementMapper {

    fun fromPresentation(model: HierarchyElementDb): HierarchyElement {
        return HierarchyElement(model.primaryKey,
                model.parentId,
                model.name,
                DataHierarchyElementMapper.fromPresentation(model.getDataHierarchyDb()))
    }

    fun fromDb(model: HierarchyElement): HierarchyElementDb {
        return HierarchyElementDb().apply {
            name = model.name
            parentId = model.parentId
            dataHierarchyId = model.dataElement?.id
        }
//        return HierarchyContainerDb(HierarchyElementDb().apply {
//            name = model.name
//            id = model.hierarchyId
//            parentId = model.parentId
//        },
//                DataHierarchyElementMapper.fromDb(model.dataHierarchyElement),
//                model.dataHierarchyElement?.pointerList?.map { PointerHierarchyMapper.fromDb(it) })
    }
}