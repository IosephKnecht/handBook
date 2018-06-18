package com.example.aamezencev.handbook.data

interface IElement {
    val name: String
    val childList: List<IElement>
    val text: String

    fun createIterator(): Iterator<IElement>
}