package com.archoid.auth.register.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object RegisterScreen: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		RegisterFragment()

}