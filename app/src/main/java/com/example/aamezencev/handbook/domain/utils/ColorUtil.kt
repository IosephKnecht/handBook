package com.example.aamezencev.handbook.domain.utils

import android.content.Context
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import java.util.*

object ColorUtil {
    fun instanceColor(context: Context, bookmarkList: List<BookmarkInfo>): List<BookmarkInfo> {
        bookmarkList.forEach {
            context.resources.obtainTypedArray(R.array.bookmark_colors).apply {
                it.color = getColor(Random().nextInt(7), context.resources.getColor(android.R.color.black))
                recycle()
            }
        }
        return bookmarkList
    }
}