import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

repositories {
    mavenCentral()
}

group = "io.github.ytg1234"
version = "1.0"

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "15"
    }
}
