plugins {
    id("feature-android-module")
}

android {
    namespace = "com.mobile.e2m.dashboard"
}

dependencies {
    implementation(project(mapOf("path" to ":dashboard-domain:home")))
    implementation(project(mapOf("path" to ":dashboard-domain:music")))
    implementation(project(mapOf("path" to ":dashboard-domain:profile")))
}
