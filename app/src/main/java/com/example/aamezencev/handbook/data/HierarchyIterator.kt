package com.example.aamezencev.handbook.data

import java.util.*

class HierarchyIterator(iterator: Iterator<IElement>) : Iterator<IElement> {
    private val stack = Stack<Iterator<IElement>>()

    init {
        stack.push(iterator)
    }

    override fun hasNext(): Boolean {
        if (stack.empty()) {
            return false
        } else {
            val iterator = stack.peek()
            if (!iterator.hasNext()) {
                stack.pop()
                return hasNext()
            } else {
                return true
            }
        }
    }

    override fun next(): IElement {
        if (hasNext()) {
            val iterator = stack.peek()
            val element = iterator.next()
            if (element is Chapter) stack.push(element.createIterator())
            return element
        } else {
            return Any() as IElement
        }
    }
}