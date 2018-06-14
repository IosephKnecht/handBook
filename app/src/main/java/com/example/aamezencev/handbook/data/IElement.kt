package com.example.aamezencev.handbook.data

interface IElement {
    val name: String
    fun createIterator(): Iterator<IElement>
}