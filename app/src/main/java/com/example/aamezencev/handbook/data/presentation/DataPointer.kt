package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class DataPointer(val id: Long,
                  val startIndex: Int,
                  val finalIndex: Int,
                  val dataHierarchyId: Int,
                  val model: ThreeDimensionalModel) {
    @HierarchyDSL
    class Builder() {
        var id = -1L
        var startIndex = -1
        var finalIndex = -1
        var dataHierarchyId = -1
        private lateinit var model: ThreeDimensionalModel

        fun model(block: ThreeDimensionalModel.Builder.() -> Unit) {
            model = ThreeDimensionalModel.Builder().apply(block).build()
        }

        fun build() = DataPointer(id, startIndex, finalIndex, dataHierarchyId, model)
    }
}