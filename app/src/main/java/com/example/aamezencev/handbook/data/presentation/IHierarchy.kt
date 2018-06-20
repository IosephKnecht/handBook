package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.parcel.IElement

interface IHierarchy : IElement, Iterable<IElement?> {
    fun isHasNesting(): Boolean
}