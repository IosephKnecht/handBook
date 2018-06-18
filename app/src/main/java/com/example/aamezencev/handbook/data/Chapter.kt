package com.example.aamezencev.handbook.data

import java.util.*

data class Chapter(override val name: String,
                   override val childList: List<IElement>) : IElement {
    override fun isHasNesting() = childList.isNotEmpty()

    private var iterator: ChapterIterator? = null

    override fun iterator(): Iterator<IElement?> {
        if (iterator == null) iterator = ChapterIterator()
        return iterator as ChapterIterator
    }

    override val text: String
        get() = throw IllegalArgumentException()

    @HierarchyDSL
    class Builder {
        var name = ""
        private var childList = mutableListOf<IElement>()
        var text: String = ""

        fun build(): IElement {
            if (childList.isNotEmpty()) {
                return Chapter(name, childList)
            } else {
                return Page(name, text)
            }
        }

        fun childs(block: Childs.() -> Unit) {
            childList.addAll(Childs().apply(block))
        }
    }

    @HierarchyDSL
    class Childs : ArrayList<IElement>() {
        fun child(block: Builder.() -> Unit) {
            add(Builder().apply(block).build())
        }
    }

    private inner class ChapterIterator : Iterator<IElement?> {
        private val stackIterator: Stack<Iterator<IElement?>>

        init {
            stackIterator = Stack()
            stackIterator.push(childList.iterator())
        }

        override fun hasNext(): Boolean {
            return stackIterator.run {
                if (isEmpty()) false else {
                    this.peek().run {
                        if (!this.hasNext()) {
                            stackIterator.pop()
                            this@ChapterIterator.hasNext()
                        } else true
                    }
                }
            }
        }

        override fun next(): IElement? {
            return stackIterator.peek().run {
                if (this.hasNext()) {
                    val element = next()
                    if (element is Chapter) stackIterator.push(element.iterator())
                    element
                } else null
            }
        }

    }
}

fun elementOf(block: Chapter.Builder.() -> Unit) = Chapter.Builder().apply(block).build()