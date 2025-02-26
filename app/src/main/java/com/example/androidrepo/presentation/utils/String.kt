package com.example.androidrepo.presentation.utils

import java.util.Locale

fun String.toTitle() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}

fun addEllipsis(text: String): String {
    return if (text.length > 80) {
        text.take(80) + "..."
    } else { text }
}