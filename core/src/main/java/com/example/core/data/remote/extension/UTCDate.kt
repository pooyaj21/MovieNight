package com.example.core.data.remote.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toUtcDate(pattern: String): Date {
    val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.parse(this)
        ?: throw IllegalArgumentException("Unparseable date: \"$this\", pattern:$pattern")
}

const val ORDER_DATE_FORMAT = "yyyy-MM-dd"