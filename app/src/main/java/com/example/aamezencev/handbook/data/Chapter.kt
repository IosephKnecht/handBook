package com.example.aamezencev.handbook.data

data class Chapter(override val name: String,
                   val childList: List<IElement>) : IElement {
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
        var childList = mutableListOf<IElement>()

        fun build(): IElement {
            if (childList.isEmpty()) return Page(name)
            else return Chapter(name, childList)
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