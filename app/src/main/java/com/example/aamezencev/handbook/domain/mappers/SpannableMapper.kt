package com.example.aamezencev.handbook.domain.mappers

import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import com.example.aamezencev.handbook.data.presentation.DataPointer

object SpannableMapper {
    fun fromSpannable(description: String, pointerList: List<DataPointer>, block: (DataPointer) -> Unit): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder(description)
        pointerList.forEach {
            spannableStringBuilder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View?) {
                    block(it)
                }

            }, it.startIndex, it.finalIndex, URLSpan.PARCELABLE_WRITE_RETURN_VALUE)
        }
        return spannableStringBuilder
    }
}