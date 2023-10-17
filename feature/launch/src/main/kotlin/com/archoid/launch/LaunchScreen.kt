package com.archoid.launch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.archoid.launch.ui.LaunchFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

data object LaunchScreen: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		LaunchFragment()
}