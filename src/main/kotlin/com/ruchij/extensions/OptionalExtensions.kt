package com.ruchij.extensions

object OptionalExtensions {
    fun <A, B> A?.fold(onEmpty: () -> B, onValue: (A) -> B): B =
        if (this == null) onEmpty() else onValue(this)
}