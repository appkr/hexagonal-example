apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

val bootJar by tasks.getting(org.springframework.boot.gradle.tasks.bundling.BootJar::class) {
    enabled = false
}

group = "com.example.hexagonal.parkinglot"

dependencies {
    implementation(project(":parking-lot:application:port-in"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
