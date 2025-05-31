apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "com.google.cloud.tools.jib")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
//    runtimeOnly("com.mysql:mysql-connector-j")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
//    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
//    testImplementation("io.kotest:kotest-extensions-spring:4.4.3")
//    testImplementation("io.mockk:mockk:1.14.2")
}
