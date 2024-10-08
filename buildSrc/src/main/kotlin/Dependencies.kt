import org.gradle.api.JavaVersion

object Versions {
    const val kotlin = "1.8.21"
    const val coreKtx = "1.8.0"
    const val hilt = "2.44"
    const val build = "7.3.0"
    const val appCompat = "1.4.2"
    const val material = "1.5.0"
    const val constraint = "2.1.4"
    const val coroutine = "1.3.2"
    const val recylerview = "1.2.1"
    const val glide = "4.13.2"
    const val viewModelLifeCycle = "2.5.1"
    const val gson = "2.9.1"
    const val navigation = "2.5.3"
    const val lottie = "5.2.0"
    const val googleService = "4.3.14"
    const val firebaseBom = "31.0.2"
    const val retrofit = "2.9.0"
    const val logging = "4.2.1"
    const val mapVersion = "18.0.0"
    const val mapUtil = "2.3.0"
    const val location = "18.0.0"
}

object Libs {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constaint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
    val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}" }
    val recylerview by lazy { "androidx.recyclerview:recyclerview:${Versions.recylerview}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideProcess by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    val lifeCycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelLifeCycle}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val firebaseBom by lazy { "com.google.firebase:firebase-bom:${Versions.firebaseBom}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val logging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.logging}" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val googleMap by lazy { "com.google.android.gms:play-services-maps:${Versions.mapVersion}" }
    val googleMapUtil by lazy { "com.google.maps.android:android-maps-utils:${Versions.mapUtil}" }
    val locationService by lazy { "com.google.android.gms:play-services-location:${Versions.location}" }
}

object Configs {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
    const val applicationId = "com.android.baseArchitecture"
    const val versionCode = 1
    const val versionName = "1.1.0"
}

object ClassPlugins {
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val build by lazy { "com.android.tools.build:gradle:${Versions.build}" }
}


object Options {
    const val jvmTarget = "11"
    val sourceCompatibility = JavaVersion.VERSION_11
    val targetCompatibility = JavaVersion.VERSION_11
}
