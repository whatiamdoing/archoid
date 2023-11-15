package com.archoid.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.archoid.main.ui.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object MainScreen: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		MainFragment()

}