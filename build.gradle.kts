plugins {
  java
  war
  id("io.freefair.lombok") version "8.13.1"
}

group = "io.github.salazar.ecommerce"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val junitVersion = "5.11.0"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}


tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

dependencies {
  compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
  implementation("org.postgresql:postgresql:42.7.5")
  implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.2")
  implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
  useJUnitPlatform()
}

tasks.named<War>("war") {
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE

  from({
    configurations.runtimeClasspath.get().map {
      if (it.isDirectory) it else zipTree(it)
    }
  })
}
