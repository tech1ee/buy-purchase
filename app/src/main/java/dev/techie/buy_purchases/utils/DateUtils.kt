package dev.techie.buy_purchases.utils

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy-MM-dd"

fun today(): String {
    val cal = Calendar.getInstance()
    cal.get(Calendar.YEAR)
    cal.get(Calendar.MONTH)
    cal.get(Calendar.DAY_OF_MONTH)
    return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(cal.time)
}

fun now(): Long {
    return System.currentTimeMillis()
}