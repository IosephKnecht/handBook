package com.example.aamezencev.handbook.data.presentation

import android.text.SpannableStringBuilder

data class Page(var description: SpannableStringBuilder,
                var marked: Boolean = false) {
}