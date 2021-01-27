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
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib"))
    }
    
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "15"
    }

    tasks.withType<Wrapper> {
        gradleVersion = "6.8.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}
