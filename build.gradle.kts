plugins {
    kotlin("jvm") version "1.3.72"
    id("com.google.cloud.tools.appengine") version "2.2.0"
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "org.example"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

val ktorVersion = "1.3.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    fun ktor(module: String, ver: String = ktorVersion) = "io.ktor:ktor-$module:$ver"
    implementation(ktor("server-core"))
    implementation(ktor("server-netty"))
}

application {
    mainClassName = "com.example.MyApplicationKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    jar {
        manifest {
            attributes(mapOf(
                "Main-Class" to application.mainClassName
            ))
        }
    }
    shadowJar {
        archiveClassifier.set("")
    }
}

appengine {
    deploy {
        projectId = "GCLOUD_CONFIG"// "GCLOUD_CONFIG"を設定するとgcloud configで設定しているプロジェクトにデプロイされます
        version = "GCLOUD_CONFIG" //"GCLOUD_CONFIG"を設定すると自動でバージョン番号が設定されます
    }
}

