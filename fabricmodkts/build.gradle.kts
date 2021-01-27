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
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }

    repositories {
        if (System.getenv("MAVEN_REPO") != null) maven(url = System.getenv("MAVEN_REPO"))
    }
}
