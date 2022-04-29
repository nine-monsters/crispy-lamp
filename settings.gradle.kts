rootProject.name = "crispy-lamp"

pluginManagement {
    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.intellij").version(extra["intellij.version"] as String)
        id("org.jetbrains.compose").version(extra["org.jetbrains.compose.version"] as String)
        id("org.jetbrains.changelog").version(extra["org.jetbrains.changelog.version"] as String)
        id("org.jetbrains.qodana").version(extra["org.jetbrains.qodana.version"] as String)
    }
}