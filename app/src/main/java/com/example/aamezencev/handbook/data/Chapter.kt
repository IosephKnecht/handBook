package com.example.aamezencev.handbook.data

data class Chapter(override val name: String,
                   override val childList: List<IElement>) : IElement {
    override val text: String
        get() = throw IllegalArgumentException()
    private var iterator: Iterator<IElement>? = null

    override fun createIterator(): Iterator<IElement> {
        if (iterator == null) {
            iterator = HierarchyIterator(childList.iterator())
        }
        return iterator as Iterator<IElement>
    }

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
}

fun elementOf(block: Chapter.Builder.() -> Unit) = Chapter.Builder().apply(block).build()