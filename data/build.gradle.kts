import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

fun LibraryExtension.createDefaultConfig() = defaultConfig {
    minSdk = Configs.minSdk
    targetSdk = Configs.targetSdk
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    buildConfigField("String", "API_URL", "\"https://jsonplaceholder.typicode.com/\"")
}

fun LibraryExtension.createBuildTypes() = buildTypes {
    getByName("release") {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        )
    }
}

android {
    compileSdk = Configs.compileSdk
    createDefaultConfig()
    createBuildTypes()
    compileOptions {
        sourceCompatibility = Options.sourceCompatibility
        targetCompatibility = Options.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Options.jvmTarget
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(Libs.coreKtx)
    implementation(Libs.hilt)
    implementation(Libs.gson)
    implementation(Libs.retrofit)
    implementation(Libs.logging)
    implementation(Libs.gsonConverter)
    implementation(Libs.locationService)
    kapt(Libs.hiltCompiler)

}