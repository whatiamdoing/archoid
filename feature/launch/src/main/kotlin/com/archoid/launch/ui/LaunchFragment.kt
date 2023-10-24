package com.archoid.launch.ui

import android.os.Bundle
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.MvvmFragment
import com.archoid.launch.R
import com.archoid.launch.di.DaggerLaunchComponent
import com.archoid.launch.di.LaunchComponent

class LaunchFragment : MvvmFragment(R.layout.fragment_launch) {

	init {
		componentBuilder = {
			DaggerLaunchComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	private val viewModel by viewModel<LaunchViewModel>()

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.navigateToNext()
	}

}