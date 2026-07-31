pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven(url = "https://www.jitpack.io")
        maven(url = "https://maven.aliyun.com/repository/releases")
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/central")
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        maven(url = "https://maven.aliyun.com/repository/public")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven(url = "https://www.jitpack.io")
        maven(url = "https://maven.aliyun.com/repository/releases")
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/central")
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        maven(url = "https://maven.aliyun.com/repository/public")
    }
}

rootProject.name = "location"
include(":app")
include(":KailLocationXposed:app")
include(":NewBlackbox:Bcore")
include(":NewBlackbox:black-reflection")
include(":NewBlackbox:compiler")
