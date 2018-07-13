package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.PointerDataDb
import com.example.aamezencev.handbook.data.presentation.DataPointer

object PointerHierarchyMapper {
    fun fromPresentation(model: PointerDataDb): DataPointer {
        return DataPointer(model.primaryKey,
                model.startIndex,
                model.finalIndex,
                model.dataHierarchyId,
                model.thrModelId)
    }

    fun fromDb(model: DataPointer): PointerDataDb {
        return PointerDataDb().apply {
            startIndex = model.startIndex
            finalIndex = model.finalIndex
            dataHierarchyId = model.dataHierarchyId.toLong()
            thrModelId = model.thrModelId
        }
    }
}