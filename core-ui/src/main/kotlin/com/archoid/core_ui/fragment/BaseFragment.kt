package com.archoid.core_ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.archoid.core_ui.di.utils.ComponentManager
import com.archoid.core_ui.di.utils.DaggerComponent
import com.archoid.global.utils.extensions.orFalse
import com.archoid.global.utils.objectScopeName
import com.archoid.global.utils.safeCast

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

	protected lateinit var componentBuilder: () -> DaggerComponent

	private var instanceStateSaved: Boolean = false
	protected lateinit var fragmentScopeName: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		fragmentScopeName = savedInstanceState?.getString(KEY_SCOPE_NAME) ?: objectScopeName()
		initComponent()
	}

	abstract fun initComponent()

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putString(KEY_SCOPE_NAME, fragmentScopeName)
		instanceStateSaved = true
	}

	override fun onResume() {
		super.onResume()
		instanceStateSaved = false
	}

	override fun onDestroy() {
		super.onDestroy()
		if (needCloseScope()) {
			ComponentManager.remove(fragmentScopeName)
		}
	}

	protected inline fun <reified T : DaggerComponent> getComponent(): T =
		ComponentManager.getOrPut(fragmentScopeName, componentBuilder) as T

	private fun needCloseScope() =
		when {
			activity?.isChangingConfigurations.orFalse() -> false
			activity?.isFinishing.orFalse() -> true
			else -> isRealRemoving()
		}

	private fun isRealRemoving(): Boolean =
		(isRemoving && !instanceStateSaved) ||
				parentFragment?.safeCast<Fragment, BaseFragment>()?.isRealRemoving().orFalse()

	private companion object {
		const val KEY_SCOPE_NAME = "SCOPE_NAME"
	}

}