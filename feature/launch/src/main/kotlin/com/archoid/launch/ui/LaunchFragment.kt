package com.archoid.launch.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.core_ui.Screens
import com.archoid.core_ui.di.dependencies.findComponentDependencies
import com.archoid.core_ui.fragment.BaseFragment
import com.archoid.launch.R
import com.archoid.launch.databinding.FragmentLaunchBinding
import com.archoid.launch.di.DaggerLaunchComponent
import com.archoid.launch.di.LaunchComponent
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LaunchFragment : BaseFragment(R.layout.fragment_launch) {

	init {
		componentBuilder = {
			DaggerLaunchComponent
				.factory()
				.create(
					dependencies = findComponentDependencies()
				)
		}
	}

	@Inject
	lateinit var router: Router

	@Inject
	lateinit var screens: Screens

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		navigateNext()
	}

	private fun navigateNext() {
		Handler(Looper.getMainLooper()).postDelayed(
			{
				router.replaceScreen(
					screens.auth()
				)
			},
			DELAY_LAUNCH_SCREEN
		)
	}

	private companion object {
		const val DELAY_LAUNCH_SCREEN = 1000L
	}

}