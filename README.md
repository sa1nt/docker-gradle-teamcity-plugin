# docker-gradle-teamcity-plugin
Gradle plugin to produce test output in Teamcity Service Message format, inspired by [Jest Teamcity reporter](https://github.com/winterbe/jest-teamcity-reporter).

The idea is that when your build happens inside a Docker container on Teamcity, you generally need to populate the test report to Teamcity so it could show you test results.

Alternatively you can enable this plugin, which, if `TEAMCITY_VERSION` env variable is present, will produce test results in the [Teamcity Service Message](https://confluence.jetbrains.com/display/TCD18/Build+Script+Interaction+with+TeamCity) format.

## Usage
See [Gradle Plugin portal](https://plugins.gradle.org/plugin/io.github.sa1nt.docker-teamcity)

### `build.gradle`
```groovy
plugins {
  id "io.github.sa1nt.docker-teamcity" version "0.1.5"
}
```

### `build.gradle.kts`
```kotlin
plugins {
    id("io.github.sa1nt.docker-teamcity") version "0.1.5"
}
```

### Optionally
You can override the env variable name the plugin uses to determine whether we're on Teamcity by

### `build.gradle` or `build.gradle.kts`
```groovy
dockerTeamcity {
  envVarName = "SOME_VAR"
}
```
