plugins {
    id ("application")
}

repositories {
    mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    implementation ("com.google.guava:guava:31.1-jre")
}

application {
    mainClass.set("main.java.com.ubo.tp.twitub.TwitubLauncher")
}

