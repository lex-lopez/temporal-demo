plugins {
    id("java")
}

group = "com.alopez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.temporal:temporal-sdk:1.31.0")
    implementation("net.minidev:json-smart:2.5.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.0")
    implementation("io.grpc:grpc-netty-shaded:1.75.0")
    testImplementation("io.temporal:temporal-testing:1.31.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("net.minidev:json-smart:2.5.2")
}

tasks.test {
    useJUnitPlatform()
}