rootProject.name = "hexagonal-example"

include(":bootstrap")

include(":car:application:exception")
include(":car:application:domain")
include(":car:application:port-in")
include(":car:application:port-out")
include(":car:application:service")
include(":car:application")
include(":car:adapter-rest")
include(":car:adapter-jpa")
