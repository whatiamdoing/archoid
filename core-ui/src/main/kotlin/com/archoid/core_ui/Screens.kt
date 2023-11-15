package com.archoid.core_ui

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface Screens {
	fun launch(): FragmentScreen
	fun auth(): FragmentScreen
	fun main(): FragmentScreen
}