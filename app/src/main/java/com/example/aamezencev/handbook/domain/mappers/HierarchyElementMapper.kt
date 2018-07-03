package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.help.HierarchyContainerDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement

object HierarchyElementMapper {

    fun fromPresentation(model: HierarchyElementDb): HierarchyElement {
        return HierarchyElement.Builder().build()
//        return HierarchyElement(model.primaryKey,
//                model.parentId,
//                model.name,
//                model.dataHierarchyId,
//                DataHierarchyElementMapper.fromPresentation(model.dataHierarchyDb))
    }

    fun fromDb(model: HierarchyElement): HierarchyContainerDb {
        return HierarchyContainerDb(HierarchyElementDb(), DataHierarchyDb(), listOf(), listOf())
//        return HierarchyContainerDb(HierarchyElementDb().apply {
//            name = model.name
//            id = model.hierarchyId
//            parentId = model.parentId
//        },
//                DataHierarchyElementMapper.fromDb(model.dataHierarchyElement),
//                model.dataHierarchyElement?.pointerList?.map { PointerHierarchyMapper.fromDb(it) })
    }
}