package io.github.sa1nt

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

open class DockerTeamcityPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.run {
            create("dockerTeamcity", DockerTeamcityExtension::class.java)
        }

        project.afterEvaluate {
            val envVar = getEnvironmentVariable(extension.envVarName)
            if (!envVar.isNullOrEmpty()) {
                println("Applying TeamcityTestListener with ${extension.envVarName} = $envVar")
                project.tasks.withType(Test::class.java) { testTask ->
                    testTask.addTestListener(TeamcityTestListener())
                }
            }
        }
    }

    private fun getEnvironmentVariable(varName: String): String? {
        return System.getProperty(varName)?: System.getenv(varName)
    }
}
