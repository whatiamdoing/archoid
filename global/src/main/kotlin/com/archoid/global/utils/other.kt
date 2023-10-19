package com.archoid.global.utils

import android.util.Log

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

infix fun Any?.logWithTag(tag: String) = Log.d(tag, this?.toString() ?: "null")