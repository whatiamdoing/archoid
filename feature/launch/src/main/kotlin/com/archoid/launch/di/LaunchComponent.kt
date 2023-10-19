package com.archoid.launch.di

import com.archoid.core_ui.di.utils.DaggerComponent
import com.archoid.core_ui.di.utils.PerFragment
import com.archoid.launch.ui.LaunchFragment
import dagger.Component

@PerFragment
@Component
interface LaunchComponent: DaggerComponent {
	fun inject(fragment: LaunchFragment)
}