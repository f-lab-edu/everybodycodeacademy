plugins {
    id("convention-java")
    id("convention-spring-boot")
}

dependencies {
    implementation(project(":common"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

