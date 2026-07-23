package com.comfortcross.liturgy.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun openUrl(context: Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No app found to open this link", Toast.LENGTH_SHORT).show()
    }
}

/** "Wednesday, July 22, 2026" */
fun LocalDate.longDisplay(): String {
    val dow = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val month = month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    return "$dow, $month $dayOfMonth, $year"
}

/** "Jul 26" */
fun LocalDate.shortDisplay(): String {
    val month = month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    return "$month $dayOfMonth"
}
