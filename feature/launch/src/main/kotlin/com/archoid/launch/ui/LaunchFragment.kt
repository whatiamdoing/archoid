package com.archoid.launch.ui

import by.kirich1409.viewbindingdelegate.viewBinding
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

	private val viewBinding by viewBinding(FragmentLaunchBinding::bind)

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

}