package com.example.aamezencev.handbook.domain.mappers

import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import com.example.aamezencev.handbook.data.presentation.DataPointer
import com.example.aamezencev.handbook.data.presentation.Page

object SpannableMapper {
    const val CHUNK_LENGTH = 1000

    fun fromSpannable(description: String, pointerList: List<DataPointer>, block: (DataPointer) -> Unit): List<Page> {
        val chunkList = piecemealDescription(description)
        val pageList = mutableListOf<Page>()
        chunkList.withIndex().forEach { chunk ->
            val spannableStringBuilder = SpannableStringBuilder(chunk.value)
            pointerList.forEach {
                val startIndex = it.startIndex + (chunk.index * CHUNK_LENGTH)
                val endIndex = it.finalIndex + (chunk.index * CHUNK_LENGTH)
                //TODO: Придумать логику для пограничных ссылок
                val temp = startIndex < chunk.value.length && endIndex < chunk.value.length
                if (temp) spannableStringBuilder.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View?) {
                        block(it)
                    }
                }, startIndex, endIndex, URLSpan.PARCELABLE_WRITE_RETURN_VALUE)
            }
            pageList.add(Page(spannableStringBuilder))
        }
        return pageList
    }

    private fun piecemealDescription(description: String): List<String> {
        var startIndex = 0
        val list = mutableListOf<String>()
        do {
            list.add(if (description.length <= CHUNK_LENGTH) description
            else description.substring(startIndex, startIndex + CHUNK_LENGTH))
            startIndex += CHUNK_LENGTH
        } while (startIndex + CHUNK_LENGTH < description.length)
        return list
    }
}