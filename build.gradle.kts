import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    application
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

    implementation(kotlin("reflect"))

    implementation("com.google.code.gson", "gson", "2.8.6")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}

application {
    mainClass.set("MainKt")
}
