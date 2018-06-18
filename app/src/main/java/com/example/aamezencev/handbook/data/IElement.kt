package com.example.aamezencev.handbook.data

interface IElement : Iterable<IElement?> {
    val name: String
    val childList: List<IElement>
    val text: String
}