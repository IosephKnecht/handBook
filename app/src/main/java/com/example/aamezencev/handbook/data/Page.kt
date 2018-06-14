package com.example.aamezencev.handbook.data

data class Page(override var name: String) : IElement {
    override fun createIterator() = NullIterator()

    class NullIterator : Iterator<IElement> {
        override fun hasNext() = false

        override fun next(): IElement = Any() as IElement
    }
}