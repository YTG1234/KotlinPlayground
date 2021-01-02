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

    implementation(kotlin("reflect"))

    api("com.google.code.gson", "gson", "2.8.6")
}
