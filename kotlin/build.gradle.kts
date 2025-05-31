plugins {
    idea
    java
    `jvm-test-suite`
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
    id("org.springframework.boot") version "3.5.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("com.google.cloud.tools.jib") version "3.4.5" apply false
}

allprojects {
    group = "com.example.hexagonal"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }
}

subprojects {
    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.10.2")
        implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")

        testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
        testImplementation("io.kotest:kotest-assertions-core:5.9.1")
        testImplementation("io.kotest:kotest-framework-datatest:5.9.1")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")
        testImplementation("io.mockk:mockk:1.14.2")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
