package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class ThreeDimensionalModel(val hierarchyId: Long,
                            val dataHierarchyId: Long,
                            val modelArray: ByteArray) {

    @HierarchyDSL
    class Builder {
        var hierarchyId: Long = -1
        var dataHierarchyId: Long = -1
        var modelArray = byteArrayOf()

        fun build() = ThreeDimensionalModel(hierarchyId, dataHierarchyId, modelArray)
    }
}