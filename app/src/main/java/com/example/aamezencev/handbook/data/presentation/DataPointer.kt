package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class DataPointer(val id: Long,
                  val startIndex: Int,
                  val finalIndex: Int,
                  val dataHierarchyId: Long,
                  val thrModelId: Long){
    @HierarchyDSL
    class Builder {
        var id = -1L
        var startIndex = -1
        var finalIndex = -1
        var dataHierarchyId = -1L
        var thrModelId = -1L

        fun build() = DataPointer(id, startIndex, finalIndex, dataHierarchyId, thrModelId)
    }
}