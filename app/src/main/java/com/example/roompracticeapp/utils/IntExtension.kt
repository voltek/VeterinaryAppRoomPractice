package com.example.roompracticeapp.utils

@JvmOverloads
fun Int?.value(default: Int = 0): Int = this ?: default