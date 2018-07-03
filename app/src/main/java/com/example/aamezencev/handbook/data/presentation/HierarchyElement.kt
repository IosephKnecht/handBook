package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class HierarchyElement(val id: Long,
                       val parentId: Long,
                       val name: String,
                       val childrenList: List<HierarchyElement>?,
                       val listData: List<DataElement>?) {
    @HierarchyDSL
    class Builder {
        var id: Long = -1L
        var parentId = -1L
        var name: String = ""
        private var listData: MutableList<DataElement> = mutableListOf()
        private var childrenList: MutableList<HierarchyElement> = mutableListOf()

        fun dataList(block: DataElementArray.() -> Unit) {
            listData.addAll(DataElementArray().apply(block))
        }

        fun childrenList(block: HierarchyElementArray.() -> Unit) {
            childrenList.addAll(HierarchyElementArray().apply(block));
        }

        fun build() = HierarchyElement(id, parentId, name, if (childrenList.isEmpty()) null else childrenList,
                if (listData.isEmpty()) null else listData)
    }

    @HierarchyDSL
    class DataElementArray : ArrayList<DataElement>() {
        fun data(block: DataElement.Builder.() -> Unit) {
            add(DataElement.Builder().apply(block).build())
        }
    }

    @HierarchyDSL
    class HierarchyElementArray : ArrayList<HierarchyElement>() {
        fun children(block: Builder.() -> Unit) {
            add(Builder().apply(block).build())
        }
    }
}

fun hierarchyElementOf(block: HierarchyElement.Builder.() -> Unit) = HierarchyElement.Builder().apply(block).build()