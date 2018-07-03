package com.example.aamezencev.handbook.data.help

import com.example.aamezencev.handbook.data.db.*

data class HierarchyContainerDb(val hierarchyElementDb: HierarchyElementDb,
                                val hierarchyDataHierarchyDb: DataHierarchyDb?,
                                val pointerList: List<PointerDataDb>?,
                                val listModel: List<ThreeDimensionalModelDb>?)