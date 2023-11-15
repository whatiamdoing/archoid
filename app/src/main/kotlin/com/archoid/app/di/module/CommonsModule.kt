package com.archoid.app.di.module

import android.content.Context
import com.archoid.core_ui.tools.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object CommonsModule {

	@Provides
	@Reusable
	fun resourceManager(context: Context) = ResourceManager(context = context)

}