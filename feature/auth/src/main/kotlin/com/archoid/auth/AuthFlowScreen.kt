package com.archoid.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AuthFlowScreen: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		AuthFlowFragment()

}