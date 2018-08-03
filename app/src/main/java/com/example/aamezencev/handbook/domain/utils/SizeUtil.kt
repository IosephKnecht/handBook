package com.example.aamezencev.handbook.domain.utils

object SizeUtil {
    private val description = listOf("БТ", "КБ", "МБ")
    private val lamda: ((Long, Int) -> Boolean) = { l, i ->
        val result = (l / i).toInt()
        if (result > 0 && result < 1024) true else false
    }

    fun sizeToString(size: Long): String {
        return when {
            lamda.invoke(size, 1024 * 1024) -> "${(size / (1024 * 1024)).toDouble()} ${description[2]}"
            lamda.invoke(size, 1024) -> "${(size / 1024).toDouble()} ${description[1]}"
            else -> "$size ${description[0]}"
        }
    }
}