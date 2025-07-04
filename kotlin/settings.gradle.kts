rootProject.name = "hexagonal-example"

include(":application-api")
include(":application-cron")

include(":car:application:exception")
include(":car:application:domain")
include(":car:application:port-in")
include(":car:application:port-out")
include(":car:application:service")
include(":car:adapter-rest")
include(":car:adapter-jpa")

include(":parking-lot:application:domain")
include(":parking-lot:application:port-in")
include(":parking-lot:application:service")
include(":parking-lot:adapter-rest")
include(":parking-lot:adapter-cron")

// 순환참조를 피하기 위해서 별도의 이름을 부여한다
// Gradle can confuse subprojects with the same name, even if they are in different paths, leading to circular dependency errors.
// parking-lot:domain -> car:domain 직접 의존보다는 car:domain을 품은 제 3의 모듈을 만드는 것도 회피 방법이다
project(":parking-lot:application:domain").name = "parking-lot-domain"
