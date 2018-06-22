package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.data.presentation.elementOf

object FakeService {
    fun getHierarchy(): IHierarchy {
        return elementOf {
            name = "Chapter 1"
            childs {
                child {
                    name = "Chapter 2"
                    childs {
                        child {
                            name = "Page 1"
                        }
                        child {
                            name = "Page 3"
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