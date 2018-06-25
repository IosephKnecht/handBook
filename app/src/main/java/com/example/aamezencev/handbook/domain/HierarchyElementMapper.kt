package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.presentation.HierarchyElement

object HierarchyElementMapper {
    fun fromPresentation(model: HierarchyElementDb): HierarchyElement {
        return HierarchyElement(model.primaryKey,
                model.parentId,
                model.name,
                model.dataHierarchyId,
                DataHierarchyElementMapper.fromPresentation(model.dataHierarchyDb))
    }
}