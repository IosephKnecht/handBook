package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.parcel.IElement

data class Page(override var name: String, override val text: String) : IHierarchy {
    override fun isHasNesting() = false

    override fun iterator(): Iterator<IElement?> {
        return NullIterator()
    }

    private class NullIterator : Iterator<IElement?> {
        override fun next(): IElement? = null

        override fun hasNext(): Boolean = false

    }

    override val childList: List<IElement>
        get() = throw IllegalArgumentException()
}