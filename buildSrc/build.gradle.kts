plugins {
	`kotlin-dsl`
	`java-library`
}

repositories {
	mavenCentral()
	google()
}

dependencies {
	implementation("com.android.tools.build:gradle:8.1.1")
	implementation(kotlin("gradle-plugin", "1.9.0"))
}