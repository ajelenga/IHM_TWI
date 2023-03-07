plugins {
    java
    application
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
}

application {
    mainClass.set("com.ubo.tp.twitub.TwitubLauncher")
}
