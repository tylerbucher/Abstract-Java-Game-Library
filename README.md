# AJGL [![Build Status](https://travis-ci.org/agent6262/Abstract-Java-Game-Library.svg?branch=master)](https://travis-ci.org/agent6262/Abstract-Java-Game-Library) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
AJGL is a crossplatform library built on top of [LWJGL3](https://www.lwjgl.org/) aimed at providing general functions 
for use in a game driven application.

## About AJGL
AJGL is licensed under [MIT](https://opensource.org/licenses/MIT) which means it is open source and available at no 
charge.

AJGL is a Java library aimed at providing useful functions in the development of game graphics 
(OpenGL) and audio (OpenAL). These functions are designed to be high-performance, yet 
also easy to use and understand in the Java environment.

AJGL is a medium-level access library but at the same time provides low-level access to various bindings through LWJGL3. 
AJGL is a fully fleshed out library providing various utilities and levels of access to bindings. As such, we welcome 
both novice and professional programmers who desire to use this library.

## Features
**Note:** Currently AJGL only provides a few OpenGL functions but more is planned.

**Provided:**
* A few OpenGL functions

**Planned:**
* Full OpenGL support
* OpenAL
* Spatial partitioning
* Object collision
* UI support (with STB bindings)
* And more basic game functions.

## Prerequisites
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* OpenGL version 2.0
* GLSL version 1.0

## Building
**Note:** If you do not have Gradle installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows 
systems in place of any 'gradle' command.

In order to build AJGL just run the `gradle build` command. Once that is finished you will find library, sources, and 
javadoc .jars exported into the `./build/libs` folder and the will be labeled like the following.
```
AJGL-x.x.x.jar
AJGL-x.x.x-javadoc.jar
AJGL-x.x.x-sources.jar
```

**Alternatively** you can include AJGL in your build.gradle file by using the following.
```
repositories {
    maven {
        name = 'reallifegames'
        url = 'https://reallifegames.net/artifactory/library-release'
    }
}

dependencies {
    compile 'org.ajgl:AJGL:x.x.x' // For compile time.
    runtime 'org.ajgl:AJGL:x.x.x' // For usage in a runtime application.
}
```