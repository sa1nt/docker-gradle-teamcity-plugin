import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.60"
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.10.0"
}

group = "com.github.sa1nt"
version = "0.1.5"

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

pluginBundle {
    website = "https://github.com/sa1nt/docker-gradle-teamcity-plugin"
    vcsUrl = "https://github.com/sa1nt/docker-gradle-teamcity-plugin"
    (plugins) {
        "docker-teamcity" {
            // id is captured from gradlePlugin extension block
            displayName = "Plugin to produce test output as Teamcity Service Messages"
            description = "Produces tests' output in Teamcity Service Message format"
            tags = listOf("teamcity", "service messages", "docker", "test output")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
