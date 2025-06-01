rootProject.name = "hexagonal-example"

include(":bootstrap")

include(":car:application:exception")
include(":car:application:domain")
include(":car:application:port-in")
include(":car:application:port-out")
include(":car:application:service")
include(":car:adapter-rest")
include(":car:adapter-jpa")

include(":parking-lot:application:domain")
include(":parking-lot:application:port-in")
include(":parking-lot:adapter-rest")

// 순환참조를 피하기 위해서 별도의 이름을 부여한다
// Gradle can confuse subprojects with the same name, even if they are in different paths, leading to circular dependency errors.
project(":parking-lot:application:domain").name = "parking-lot-domain"
