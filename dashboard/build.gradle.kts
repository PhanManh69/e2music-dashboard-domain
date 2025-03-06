plugins {
    id("feature-android-module")
}

android {
    namespace = "com.mobile.e2m.dashboard"
}

dependencies {
    implementation(vc.concurrentCoroutines)
    implementation(vc.bundleExoPlayer)

    implementation(project(mapOf("path" to ":dashboard-domain:home")))
    implementation(project(mapOf("path" to ":dashboard-domain:music")))
    implementation(project(mapOf("path" to ":dashboard-domain:profile")))
    implementation(project(mapOf("path" to ":daily:playmusic")))
    implementation(project(mapOf("path" to ":daily:menu")))
}
