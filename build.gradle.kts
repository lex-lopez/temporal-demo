plugins {
    id("java")
}

group = "com.alopez"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.temporal:temporal-sdk:1.32.1")
    implementation("org.slf4j:slf4j-nop:2.0.17")
    testImplementation("io.temporal:temporal-testing:1.31.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.20.0")

    // The below are needed to patch a vulnerability on transitive dependencies from temporal-sdk:1.32.1
    implementation("net.minidev:json-smart:2.5.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.0")
    implementation("io.grpc:grpc-netty-shaded:1.75.0")
    implementation(platform("io.grpc:grpc-bom:1.75.0"))
    testImplementation("net.minidev:json-smart:2.5.2")

}

tasks.test {
    useJUnit()
}

tasks.register<JavaExec>("runWorker") {
    group = "temporal"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("helloworldapp.workers.HelloWorldWorker")
}

tasks.register<JavaExec>("runWorkflow") {
    group = "temporal"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("helloworldapp.workflows.InitiateHelloWorldWorkflow")
}