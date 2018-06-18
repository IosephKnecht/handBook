package com.example.aamezencev.handbook.data

data class Page(override var name: String, override val text: String) : IElement {
    override val childList: List<IElement>
        get() = throw IllegalArgumentException()

    override fun createIterator() = NullIterator()

    class NullIterator : Iterator<IElement> {
        override fun hasNext() = false

        override fun next(): IElement = Any() as IElement
    }
}