package com.archoid.launch.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.archoid.core_ui.fragment.BaseFragment
import com.archoid.launch.R
import com.archoid.launch.databinding.FragmentLaunchBinding
import com.archoid.launch.di.DaggerLaunchComponent
import com.archoid.launch.di.LaunchComponent

class LaunchFragment : BaseFragment(R.layout.fragment_launch) {

	init {
		componentBuilder = {
			DaggerLaunchComponent.create()
		}
	}

	private val viewBinding by viewBinding(FragmentLaunchBinding::bind)

	override fun initComponent() {
		getComponent<LaunchComponent>().inject(this)
	}

}