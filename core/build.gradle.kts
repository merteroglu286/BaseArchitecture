plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Configs.compileSdk
    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Options.sourceCompatibility
        targetCompatibility = Options.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Options.jvmTarget
    }
    buildFeatures {
        viewBinding= true
    }
}

dependencies {
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)
    implementation(Libs.recylerview)
    implementation(Libs.glide)
    annotationProcessor(Libs.glideProcess)
    implementation(Libs.coreKtx)
    implementation(Libs.coroutine)
    implementation(Libs.lifeCycleViewModel)


    implementation(project(":domain"))

    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
}