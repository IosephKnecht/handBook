package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.data.elementOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        hierarchy.childList.forEach { }

        val allList = mutableListOf<IElement?>()
        hierarchy.forEach {
            allList.add(it)
        }

    }
}
