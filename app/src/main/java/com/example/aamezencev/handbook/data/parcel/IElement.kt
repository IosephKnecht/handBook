package com.example.aamezencev.handbook.data.parcel

interface IElement {
    val name: String
    val childList: List<IElement>
    val text: String
}