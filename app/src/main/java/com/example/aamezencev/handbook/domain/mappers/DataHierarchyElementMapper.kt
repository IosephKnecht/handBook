package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.presentation.DataElement

object DataHierarchyElementMapper {
    fun fromPresentation(model: DataHierarchyDb?): DataElement? {
        return DataElement.Builder().build()
//        if (model != null && !model.isEmpty) {
//            return DataHierarchyElement(model.primaryKey, model.description,
//                    model.pointerList.map { PointerHierarchyMapper.fromPresentation(it) })
//        } else {
//            return null
//        }
    }

    fun fromDb(model: DataElement?): DataHierarchyDb {
        return DataHierarchyDb()
//        return DataHierarchyDb().apply {
//            description = model?.description
//        }
    }
}