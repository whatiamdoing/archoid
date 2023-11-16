package com.archoid

import com.archoid.dependencies.Dependencies
import com.archoid.dependencies.Plugins
import com.archoid.dependencies.ProjectConfig
import com.archoid.modules.Modules
import com.archoid.utils.implementation
import com.archoid.utils.ksp
import com.archoid.utils.libraryExtension
import com.archoid.utils.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

open class BuildPlugin : Plugin<Project> {

	override fun apply(project: Project) {
		project.addAndroidLibraryExtensionConfig()

		if (project.isNeedDI) {
			project.addDIDependencies()
		}

		if (project.isFeature) {
			project.addFeatureDependencies()
		}
	}

}

private fun Project.addAndroidLibraryExtensionConfig() = libraryExtension.apply {
	compileSdk = ProjectConfig.compileSdkVersion
	buildToolsVersion = ProjectConfig.buildToolsVersion

	defaultConfig {
		minSdk = ProjectConfig.minSdkVersion

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	flavorDimensions.add("type")

	productFlavors {
		create("mvvm") {
			dimension = "type"
		}

		create("mvp") {
			dimension = "type"
		}

		create("elm") {
			dimension = "type"
		}
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}

	viewBinding {
		enable = true
	}
}

/**
 * Contains name of modules, using DI.
 */
private val modulesUsingDI = setOf(
	"core-ui",
	"data",
	"data-api-local",
	"data-api-remote",
	"navigation",
	"global"
)

private val Project.isNeedDI: Boolean
	get() = modulesUsingDI.contains(name) || isFeature

private val Project.isFeature: Boolean
	get() = displayName.contains("feature")

private fun Project.addDIDependencies() {
	plugins.apply(Plugins.ksp)

	dependencies {
		implementation(Dependencies.dagger)
		ksp(Dependencies.daggerCompiler)
	}
}

private fun Project.addFeatureDependencies() {

	withProjects(
		Modules.CoreUi,
		Modules.Global,
		Modules.Resources,
		Modules.Domain
	)

	withLibs(
		Dependencies.AndroidX.fragment,
		Dependencies.viewBindingDelegate,
		Dependencies.Navigation.cicerone,
		Dependencies.material
	)

}