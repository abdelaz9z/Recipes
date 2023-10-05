// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        val nav_version = "2.7.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
    repositories {
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
    id("com.android.library") version "8.1.1" apply false
}