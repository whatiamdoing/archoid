package com.archoid.global.utils

import android.util.Log

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

infix fun Any?.logWithTag(tag: String) = Log.d(tag, this?.toString() ?: "null")

inline fun <reified T, reified R> T.safeCast(): R? where R: T =
	this as? R

inline fun <reified T, reified R> T.strictCast(): R where R: T =
	this as R