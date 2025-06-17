plugins {
    java
    id("convention-java")
    id("convention-spring-boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":common"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

