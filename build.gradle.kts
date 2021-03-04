plugins {
    java
    kotlin("jvm") version "1.4.31"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.example"
version = "1.0v"

repositories {
    maven("https://papermc.io/repo/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.bukkit")
    compileOnly("org.spigotmc")
    compileOnly(kotlin("stdlib"))
    val transitive = Action<ExternalModuleDependency> { isTransitive = false }
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks.jar {
    from(
        shade.map {
            if (it.isDirectory)
                it
            else
                zipTree(it)
        }
    )
}

/*tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    shadowJar {
        classifier = null
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(120, "seconds")
}*/
