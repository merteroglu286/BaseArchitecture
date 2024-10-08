plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
  //  id("com.google.gms.google-services") "google_services koyunca burayı aç"
    //id("com.google.firebase.crashlytics")

}


android {

    signingConfigs {
        create("betaKey") {
            storeFile = file(".betakey")
            keyAlias = ".betakey"
            keyPassword = ".betakey"
            storePassword = ".betakey"
        }

        create("release") {
            storeFile = file(".betakey")
            keyAlias = ".betakey"
            keyPassword = ".betakey"
            storePassword = ".betakey"
        }
    }


    compileSdk = Configs.compileSdk
    defaultConfig {
        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("betaKey")
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
        viewBinding = true
    }


}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.hilt)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.constaint)
    implementation(Libs.coroutine)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUI)
    implementation(Libs.googleMap)
    implementation(Libs.googleMapUtil)
    implementation(Libs.locationService)
    implementation(platform(Libs.firebaseBom))


    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
  /*  implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")*/
    implementation("com.google.firebase:firebase-messaging")



    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))

    kapt(Libs.hiltCompiler)


}
