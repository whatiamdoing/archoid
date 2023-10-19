package com.archoid.global.utils.extensions

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true