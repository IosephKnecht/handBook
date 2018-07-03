package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.presentation.HierarchyElement

object FakeService {
    fun buildFakeHierarchy(): List<HierarchyElement> {
        return listOf()
//        val e1 = hierarchyElementOf {
//            name = "Chapter 1"
//            hierarchyId = 1
//            parentId = null
//        }
//
//        val e2 = hierarchyElementOf {
//            name = "Page 1"
//            parentId = 1
//            hierarchyId = 2
//            dataHierarchyElement {
//                description = "very interesting text"
//                pointers {
//                    pointer {
//                        startIndex = 0
//                        finalIndex = 5
//                        thrModel {
//                            modelArray = byteArrayOf(1, 2, 3, 4, 5, 6)
//                        }
//                    }
//                }
//            }
//        }
//
//        val e3 = hierarchyElementOf {
//            name = "Chapter 2"
//            hierarchyId = 3
//            parentId = null
//        }
//
//        val e4 = hierarchyElementOf {
//            name = "Page 2"
//            hierarchyId = 4
//            parentId = 3
//            dataHierarchyElement {
//                description = "very very interesting text"
//                pointers {
//                    pointer {
//                        startIndex = 5
//                        finalIndex = 10
//                        thrModel {
//                            modelArray = byteArrayOf(22,7,9,1,23,4)
//                        }
//                    }
//                }
//            }
//        }
//
//        return listOf(e1, e2, e3, e4)
    }
}