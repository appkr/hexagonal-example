apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

val bootJar by tasks.getting(org.springframework.boot.gradle.tasks.bundling.BootJar::class) {
    enabled = false
}

group = "com.example.hexagonal.car"

dependencies {
    implementation(project(":car:application:port-in"))
    implementation(project(":car:application:port-out"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-tx")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
