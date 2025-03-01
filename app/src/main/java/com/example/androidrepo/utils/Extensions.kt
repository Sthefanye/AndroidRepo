package com.example.androidrepo.utils

import android.view.View
import java.util.Locale

fun String.toTitle() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}

fun addEllipsis(text: String, limit: Int): String {
    return if (text.length > limit) {
        text.take(80) + "..."
    } else { text }
}

fun formatPRNumber(number: Int) = "#${number}:"


fun View.isVisible(isShowLoading: Boolean, container: View) {
    if (isShowLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    } else {
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}