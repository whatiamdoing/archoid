package com.archoid.auth.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.archoid.auth.login.ui.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal object LoginScreen: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		LoginFragment()

}