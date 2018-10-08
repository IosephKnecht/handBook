package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class DataElement(val id: Long,
                  val description: String,
                  val pointerList: List<DataPointer>) {
    @HierarchyDSL
    class Builder {
        var id = -1L
        var description = ""
        private var pointerList: MutableList<DataPointer> = mutableListOf()

        fun pointers(block: PointerArray.() -> Unit){
            pointerList.addAll(PointerArray().apply(block))
        }

        fun build() = DataElement(id, description, pointerList)
    }
    @HierarchyDSL
    class PointerArray : ArrayList<DataPointer>() {
        fun pointer(block: DataPointer.Builder.() -> Unit) {
            add(DataPointer.Builder().apply(block).build())
        }
    }
}