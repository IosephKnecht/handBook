package com.example.aamezencev.handbook.data

import android.os.Parcelable

interface IElement : Iterable<IElement?> {
    val name: String
    val childList: List<IElement>
    val text: String

    fun isHasNesting(): Boolean
}