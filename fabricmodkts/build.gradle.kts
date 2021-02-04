buildscript {
	repositories {
		jcenter()
	}
	dependencies {
		classpath("org.jfrog.buildinfo:build-info-extractor-gradle:4.+")
	}
}

plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "io.github.ytg1234"
version = "1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host"))
    api(kotlin("scripting-common"))

    implementation(kotlin("reflect"))

    api("com.google.code.gson", "gson", "2.8.6")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") { // yeah this Kotlin but named mavenJava just so my script works
            from(components["kotlin"])
        }
    }
}

apply(from = "https://raw.githubusercontent.com/YTG1234/scripts/main/scripts/gradle/artifactory.gradle")
