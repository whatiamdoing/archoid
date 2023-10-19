package com.archoid.core_ui.di.dependencies

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

interface ComponentDependencies

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ComponentDependencyKey(val value: KClass<out ComponentDependencies>)

typealias ChildComponentDependenciesHolder = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

interface HasChildDependencies {
	val dependencies: ChildComponentDependenciesHolder
}

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T {
	var dependenciesHolder = parentFragment

	while (parentFragment != null && dependenciesHolder !is HasChildDependencies) {
		dependenciesHolder = dependenciesHolder?.parentFragment
	}

	val childDependencies =
		dependenciesHolder as? HasChildDependencies
			?: activity as? HasChildDependencies
			?: activity?.application as? HasChildDependencies

	childDependencies?.dependencies?.get(key = T::class.java)?.let { dependencies -> return dependencies as T }

	throw IllegalStateException("Can't find suitable ${HasChildDependencies::class.java.simpleName} for ${this::class.java.canonicalName}")
}