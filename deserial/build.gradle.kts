plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // For JSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

    // For boilerplate code generation
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    // For XML
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")

    // For CSV
    implementation("com.opencsv:opencsv:5.7.1")

    // Unit Test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()
}