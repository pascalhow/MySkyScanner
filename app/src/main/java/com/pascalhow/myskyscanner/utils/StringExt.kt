package com.pascalhow.myskyscanner.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDuration(format: String) : String {
    val hours = this.toInt() / 60
    val minutes = this.toInt() % 60
    return String.format(format, hours, minutes)
}

fun String.changeFormat(oldFormat: String, newFormat: String): String {
    val parser = SimpleDateFormat(oldFormat, Locale.ENGLISH)
    val formatter = SimpleDateFormat(newFormat, Locale.ENGLISH)
    return formatter.format(parser.parse(this))
}
