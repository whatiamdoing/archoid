import com.archoid.dependencies.Dependencies
import com.archoid.modules.Modules
import com.archoid.utils.withProjects

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.devtools.ksp")
}

android {
	namespace = "com.archoid.app"
	compileSdk = com.archoid.dependencies.ProjectConfig.compileSdkVersion

	defaultConfig {
		applicationId = com.archoid.dependencies.ProjectConfig.applicationId
		minSdk = com.archoid.dependencies.ProjectConfig.minSdkVersion
		targetSdk = com.archoid.dependencies.ProjectConfig.targetSdkVersion
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

	kotlinOptions {
		jvmTarget = "1.8"
	}

	viewBinding {
		enable = true
	}
}

withProjects(
	Modules.Domain,
	Modules.Data,
	Modules.CoreUi,
	Modules.Navigation
)

dependencies {
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	implementation(Dependencies.dagger)
	ksp(Dependencies.daggerCompiler)

	implementation(Dependencies.Kotlin.coroutines)
	implementation(Dependencies.viewBindingDelegate)
}