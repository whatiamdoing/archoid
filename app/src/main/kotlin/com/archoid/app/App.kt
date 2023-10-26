package com.archoid.app

import android.app.Application
import com.archoid.app.di.AppComponent

class App: Application() {

	override fun onCreate() {
		super.onCreate()
		appComponent = AppComponent.start()
	}

	companion object {
		lateinit var appComponent: AppComponent
	}

}