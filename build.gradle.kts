import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.60"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = "com.github.sa1nt"
version = "0.1.3"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.assertj:assertj-core:3.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.0")
}

gradlePlugin {
    plugins {
        create("docker-teamcity") {
            id = "com.github.sa1nt.docker-teamcity"
            implementationClass = "com.github.sa1nt.DockerTeamcityPlugin"
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
