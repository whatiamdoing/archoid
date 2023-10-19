package com.archoid

import com.archoid.dependencies.Dependencies
import com.archoid.dependencies.Plugins
import com.archoid.dependencies.ProjectConfig
import com.archoid.modules.Modules
import com.archoid.utils.implementation
import com.archoid.utils.ksp
import com.archoid.utils.libraryExtension
import com.archoid.utils.withProjects
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

open class BuildPlugin : Plugin<Project> {

	override fun apply(project: Project) {
		project.addAndroidLibraryExtensionConfig()

		if (project.isRequireDI) {
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

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}

	viewBinding {
		enable = true
	}
}

private val modulesRequiringDI = listOf(
	"data",
	"data-api-local",
	"data-api-remote",
	"navigation"
)

internal val Project.isRequireDI: Boolean
	get() = modulesRequiringDI.contains(name) || isFeature

internal val Project.isFeature: Boolean
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
		Modules.CoreUi
	)

	dependencies {
		implementation(Dependencies.AndroidX.fragment)
		implementation(Dependencies.viewBindingDelegate)
		implementation(Dependencies.Navigation.cicerone)
	}
}