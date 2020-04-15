plugins {
    kotlin("jvm") version "1.3.72"
    id("com.google.cloud.tools.appengine") version "2.2.0"
    application
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

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    appengineStage {

        doLast {
            copy {
                from(project.configurations.runtimeClasspath)
                into(File(stagingExtension.stagingDirectory, "libs"))
            }
            val appYaml = File(stagingExtension.stagingDirectory, "app.yaml").readText()
            File(stagingExtension.stagingDirectory, "app.yaml")
                .writeText(appYaml.replace("{{mainClassName}}", application.mainClassName))
        }
    }
}

appengine {
    deploy {
        projectId = "<YOUR_PROJECT_ID>"// "GCLOUD_CONFIG"を設定するとgcloud configで設定しているプロジェクトにデプロイされます
        version = "GCLOUD_CONFIG" //"GCLOUD_CONFIG"を設定すると自動でバージョン番号が設定されます
    }
}

application {
    mainClassName = "com.example.MyApplicationKt"
}
