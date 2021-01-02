import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

group = "io.github.ytg1234"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host"))
    implementation(kotlin("scripting-common"))

    implementation(project(":fabricmodkts"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}
