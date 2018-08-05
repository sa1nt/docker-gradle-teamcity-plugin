package com.github.sa1nt

import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.TestResult.ResultType

/**
 * See: https://github.com/winterbe/jest-teamcity-reporter/blob/master/index.js as inspiration source
 * See: https://confluence.jetbrains.com/display/TCD18/Build+Script+Interaction+with+TeamCity for formatting specifics
 */
class TeamcityTestListener : TestListener {
    override fun beforeSuite(suite: TestDescriptor) {
        println("##teamcity[testSuiteStarted name='${teamCityEscape(suite.name)}']")
    }

    override fun beforeTest(testDescriptor: TestDescriptor) {
        println("##teamcity[testStarted name='${teamCityEscape(testDescriptor.name)}']")
    }

    override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
        val escapedTestName = teamCityEscape(testDescriptor.name)
        when (result.resultType) {
            ResultType.FAILURE -> println("##teamcity[testFailed name='$escapedTestName' message='FAILED' " +
                    "details='${teamCityEscape(result.exceptions.toString())}']")
            ResultType.SKIPPED -> println("##teamcity[testIgnored name='$escapedTestName' message='${result.resultType}']")
            else -> {} // TC assumes the test is successful
        }
        println("##teamcity[testFinished name='$escapedTestName' duration='${result.endTime - result.startTime}']")
    }

    override fun afterSuite(suite: TestDescriptor, result: TestResult) {
        println("##teamcity[testSuiteFinished name='${teamCityEscape(suite.name)}' duration='${result.endTime - result.startTime}']")
    }

    private fun teamCityEscape(s: String) : String {
        return s.replace("'", "|'")
                .replace("\n", "|n")
                .replace("\r", "|r")
                .replace("[", "|[")
                .replace("]", "|]")
    }
}
