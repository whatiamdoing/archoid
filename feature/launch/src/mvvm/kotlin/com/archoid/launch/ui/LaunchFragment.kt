package com.archoid.launch.ui

import android.os.Bundle
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.MvvmFragment
import com.archoid.launch.di.LaunchComponent
import com.archoid.launch.R
import com.archoid.launch.di.DaggerLaunchComponent

class LaunchFragment : MvvmFragment<LaunchViewModel>(R.layout.fragment_launch) {

	init {
		componentBuilder = {
			DaggerLaunchComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

	override val viewModel by viewModel<LaunchViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.navigateToNext()
	}

}