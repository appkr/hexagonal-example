import com.google.cloud.tools.jib.gradle.JibExtension

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "com.google.cloud.tools.jib")

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    mainClass.set("com.example.hexagonal.HexagonalExampleApiApplicationKt")
}

configure<JibExtension> {
    from {
        image = "amazoncorretto:21.0.7"
    }

    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
        ports = listOf("8080")
    }
}

dependencies {
    implementation(project(":car:application:service"))
    implementation(project(":car:adapter-rest"))
    implementation(project(":car:adapter-jpa"))

    implementation(project(":parking-lot:application:service"))
    implementation(project(":parking-lot:adapter-rest"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
//    runtimeOnly("com.mysql:mysql-connector-j")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
