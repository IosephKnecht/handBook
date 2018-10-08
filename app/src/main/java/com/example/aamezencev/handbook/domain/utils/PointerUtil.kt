package com.example.aamezencev.handbook.domain.utils

import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import com.example.aamezencev.handbook.data.presentation.DataPointer
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.domain.mappers.PageMapper

object PointerUtil {
    fun pastePointers(pageList: List<Page>,
                      pointerList: MutableList<DataPointer>,
                      block: (DataPointer) -> Unit): List<Page> {
        val iterator = pointerList.iterator()
        while (iterator.hasNext()) {
            val pointer = iterator.next()
            pageList.withIndex().forEach {
                val scaleLength = it.value.description.length + (PageMapper.CHUNK_LENGTH * it.index)
                //TODO Придумать алгоритм для пограничных ссылок...
                if (pointer.startIndex >= scaleLength * it.index && pointer.finalIndex < scaleLength) {
                    it.value.description.setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View?) {
                            block(pointer)
                        }
                    }, pointer.startIndex, pointer.finalIndex, URLSpan.PARCELABLE_WRITE_RETURN_VALUE)
                    iterator.remove()
                    return@forEach
                }
            }
        }
        return pageList
    }
}