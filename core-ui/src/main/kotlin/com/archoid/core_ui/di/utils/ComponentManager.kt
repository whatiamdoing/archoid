package com.archoid.core_ui.di.utils

interface DaggerComponent

object ComponentManager {

	private val components = mutableMapOf<String, DaggerComponent>()

	fun <T : DaggerComponent> getOrPut(
		componentName: String,
		componentBuilder: () -> T
	): DaggerComponent =
		components.getOrPut(
			key = componentName,
			defaultValue = componentBuilder::invoke
		)

	fun remove(componentName: String): DaggerComponent? =
		components.remove(componentName)

	fun clear() {
		components.clear()
	}

	fun get(componentName: String) =
		components[componentName]

}