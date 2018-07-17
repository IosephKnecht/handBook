package com.example.aamezencev.handbook.data.parcel

interface IElement<T> {
    val name: String
    val childList: List<T>
    val text: String
}