package com.github.sa1nt

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class DockerTeamcityPluginTest {
    lateinit var tempDir: File

    @BeforeEach
    internal fun setUp() {
        tempDir = createTempDir()
    }

    @Test
    internal fun `default envVarName`() {
        File(tempDir, "build.gradle").run {
            writeText("""
                plugins {
                  id "com.github.sa1nt.docker-teamcity"
                }
            """.trimIndent())
        }

        val buildResult = GradleRunner.create()
                .withProjectDir(tempDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(buildResult.output.contains("Hello Plugin TEAMCITY_VERSION"))
    }

    @Test
    internal fun `changed envVarName`() {
        val changedEnvVarName = "SOME_VAR"
        File(tempDir, "build.gradle").run {
            writeText("""
                plugins {
                  id "com.github.sa1nt.docker-teamcity"
                }

                dockerTeamcity {
                  envVarName = "$changedEnvVarName"
                }
            """.trimIndent())
        }

        val buildResult = GradleRunner.create()
                .withProjectDir(tempDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(buildResult.output.contains("Hello Plugin $changedEnvVarName"))
    }
}
