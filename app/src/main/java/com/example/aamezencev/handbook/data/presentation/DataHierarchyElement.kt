package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class DataHierarchyElement(val hierarchyId: Long,
                           val description: String,
                           val threeDimensionalModels: List<ThreeDimensionalModel>,
                           val pointerList: List<PointerHierarchy>) {
    @HierarchyDSL
    class Builder {
        var hierarchyId: Long = -1
        var description = ""
        private var threeDimensionalModel = arrayListOf<ThreeDimensionalModel>()
        private var pointers = arrayListOf<PointerHierarchy>()

        fun threeDimensionalModel(block: ThreeDimensionalModelList.() -> Unit) {
            threeDimensionalModel.addAll(ThreeDimensionalModelList().apply(block))
        }

        fun pointers(block: PointerList.() -> Unit) {
            pointers.addAll(PointerList().apply(block))
        }

        fun build() = DataHierarchyElement(hierarchyId, description, threeDimensionalModel, pointers)
    }

    class ThreeDimensionalModelList : ArrayList<ThreeDimensionalModel>() {
        fun thrModel(block: ThreeDimensionalModel.Builder.() -> Unit) {
            add(ThreeDimensionalModel.Builder().apply(block).build())
        }
    }

    class PointerList : ArrayList<PointerHierarchy>() {
        fun pointer(block: PointerHierarchy.Builder.() -> Unit) {
            add(PointerHierarchy.Builder().apply(block).build())
        }
    }
}