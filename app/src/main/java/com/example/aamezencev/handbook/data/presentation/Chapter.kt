package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL
import com.example.aamezencev.handbook.data.parcel.IElement
import java.util.*

data class Chapter(override val name: String,
                   override val childList: List<IHierarchy>) : IHierarchy {
    override fun isHasNesting() = childList.isNotEmpty()

    private var iterator: ChapterIterator? = null

    override fun iterator(): Iterator<IHierarchy?> {
        if (iterator == null) iterator = ChapterIterator()
        return iterator as ChapterIterator
    }

    override val text: String
        get() = throw IllegalArgumentException()

    @HierarchyDSL
    class Builder {
        var name = ""
        private var childList = mutableListOf<IHierarchy>()
        var text: String = ""

        fun build(): IHierarchy {
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
    class Childs : ArrayList<IHierarchy>() {
        fun child(block: Builder.() -> Unit) {
            add(Builder().apply(block).build())
        }
    }

    private inner class ChapterIterator : Iterator<IHierarchy?> {
        private val stackIterator: Stack<Iterator<IHierarchy?>>

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

        override fun next(): IHierarchy? {
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