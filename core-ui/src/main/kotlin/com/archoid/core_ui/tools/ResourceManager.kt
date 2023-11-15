package com.archoid.core_ui.tools

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceManager @Inject constructor(
	private val context: Context
) {

	fun getString(@StringRes resId: Int) = context.getString(resId)

}