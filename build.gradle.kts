import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

val javaVersion = JavaVersion.VERSION_13
val genSourceDir = file("$buildDir/kotlin-models/src/main/kotlin")

plugins {
	id("org.springframework.boot").version("2.5.5")
	id("io.spring.dependency-management").version("1.0.11.RELEASE")
    id("org.openapi.generator").version("5.2.1")
	kotlin("jvm").version("1.5.10")
	kotlin("plugin.spring").version("1.5.10")
}

sourceSets {
    main {
        java {
            srcDir(file("src/main/kotlin"))
            srcDir(genSourceDir)
        }

    }

    test {
        java {
            srcDir(file("src/test/kotlin"))
        }
    }
}


//apply() plugin: 'org.openapi.generator'

group = "io.gimly.tangem"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenCentral()
}

dependencies {

    implementation("com.github.tangem.tangem-sdk-android:core:develop-92")
    implementation("com.github.tangem.tangem-sdk-android:jvm:develop-92")
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.2.0")
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.6") {
        exclude(module = "spring-boot-starter-logging")
        exclude(module = "logback-classic")
    }
    implementation("org.reactivestreams:reactive-streams:1.0.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")

    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

    implementation("io.swagger", "swagger-annotations", "1.6.0")
    implementation("org.modelmapper", "modelmapper", "2.3.6")
    implementation("org.openapitools", "jackson-databind-nullable", "0.2.1")

    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.1"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")


    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Task for generating the client code for connecting with the Labracore API
val generateKotlinModels by tasks.register("generateKotlinModels", GenerateTask::class) {
    enabled = true

    // Name of the Client code generator.
    generatorName.set("kotlin")

    // Set output directory, specification and configuration.
    outputDir.set("$buildDir/kotlin-models")
    inputSpec.set("${rootDir}/src/main/resources/api.yaml")
//    configFile.set("${rootDir}/config.json")

    apiPackage.set("io.gimly.generated.card.api")
    invokerPackage.set("io.gimly.generated.card.invoker")
    modelPackage.set("io.gimly.generated.card.model")

    // Mark the output directory for this task to prevent gradle from marking it as stale
    outputs.dir("$buildDir/kotlin-models")
}

val generateMarkdown by tasks.register("generateMarkdown", GenerateTask::class) {
    generatorName.set("markdown")

    // Set output directory, specification and configuration.
    outputDir.set("${rootDir}/docs/api")
    inputSpec.set("${rootDir}/src/main/resources/api.yaml")
    apiPackage.set("io.gimly.generated.card.api")
    invokerPackage.set("io.gimly.generated.card.invoker")
    modelPackage.set("io.gimly.generated.card.model")

    // Mark the output directory for this task to prevent gradle from marking it as stale
    outputs.dir("$rootDir/docs/api")
}

