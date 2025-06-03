import com.google.cloud.tools.jib.gradle.JibExtension

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "com.google.cloud.tools.jib")

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    mainClass.set("com.example.hexagonal.HexagonalExampleCronApplicationKt")
}

configure<JibExtension> {
    from {
        image = "amazoncorretto:21.0.7"
    }

    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
}

dependencies {
    implementation(project(":car:application:service"))
    implementation(project(":car:adapter-jpa"))

    implementation(project(":parking-lot:application:service"))
    implementation(project(":parking-lot:adapter-cron"))

    // actuator endpoint 및 앱이 무한루프로 작동하기 위해 web이 필요하다
    // 같은 인스턴스에서 api, cron을 동시에 실행하면 8080 포트 충돌이 발생함의 유의한다
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
//    runtimeOnly("com.mysql:mysql-connector-j")
}
