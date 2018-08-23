package com.example.aamezencev.handbook.domain.mappers

import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService

object PageMapper {
    const val CHUNK_LENGTH = 1000

    fun map(dataElement: DataElement,
            sharedPreferenceService: SharedPreferenceService): List<Page> {
        return piecemealDescription(dataElement.description).withIndex()
            .map {
                sharedPreferenceService.existBookmarkInDatabase(dataElement.id, it.index.toLong()).run {
                    Page(SpannableStringBuilder(it.value), this)
                }
            }

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