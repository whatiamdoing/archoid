import com.archoid.dependencies.Dependencies
import com.archoid.utils.withLibs

plugins {
	`java-library`
	id("org.jetbrains.kotlin.jvm")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}


withLibs(
	Dependencies.Kotlin.coroutines
)