plugins {
    java
    kotlin("jvm") version "1.4.31"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.example"
version = "1.0v"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://papermc.io/repo/repository/maven-public")
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
    Action<ExternalModuleDependency> { isTransitive = false }
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
