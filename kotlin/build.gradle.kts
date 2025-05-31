plugins {
    idea
    java
    `java-library`
    `jvm-test-suite`
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
    id("org.springframework.boot") version "3.5.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("com.google.cloud.tools.jib") version "3.4.5" apply false
}

ktlint {
    // Class org.jetbrains.kotlin.lexer.KtTokens does not have member field 'org.jetbrains.kotlin.lexer.KtModifierKeywordToken HEADER_KEYWORD'
    // gradle plugin이 사용하는 ktlint 엔진 버전이 kotlin 2.x 버전과 호환되지 않아 ktlint 엔진 버전을 직접 선언함
    version.set("1.3.1")
}

allprojects {
    group = "com.example"
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
        withSourcesJar()
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
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
