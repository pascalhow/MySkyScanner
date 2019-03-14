package com.pascalhow.myskyscanner.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDuration(format: String) : String {
    val hours = this.toInt() / 60
    val minutes = this.toInt() % 60
    return String.format(format, hours, minutes)
}

fun String.formatTime(format: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(parser.parse(this))
}
