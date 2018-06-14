package com.example.aamezencev.handbook

import com.example.aamezencev.handbook.data.elementOf
import org.junit.Test

class ExampleHierarchyDsl {
    @Test
    fun main() {
        val hierarchy = elementOf {
            name = "Chapter 1"
            childs {
                child {
                    name = "Chapter 2"
                    childs {
                        child {
                            name = "Page 1"
                        }
                    }
                }
                child {
                    name = "Page 2"
                }
            }
        }
    }
}