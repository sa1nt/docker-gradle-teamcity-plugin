package com.github.sa1nt

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

open class DockerTeamcityPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.run {
            create("dockerTeamcity", DockerTeamcityExtension::class.java)
        }

        project.afterEvaluate {
            val envVal = System.getenv(extension.envVarName)
            if (!envVal.isNullOrEmpty()) {
                println("Applying TeamcityTestListener with ${extension.envVarName} = $envVal")
                with(project.tasks) {
                    withType(Test::class.java) {
                        it.addTestListener(TeamcityTestListener())
                    }
                }
            }
        }
    }
}
