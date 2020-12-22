# Project Setup
## Java & Gradle
This project uses Java 11 & Gradle 6.7.1

## Lombok
Lombok Project helps us avoiding boilerplate code on POJO class

[Setup on IntelliJ](https://projectlombok.org/setup/intellij)

[Setup on Eclipse](https://projectlombok.org/setup/eclipse)

## Spring Boot & JPA
Spring Boot & JPA is used to make DB operation easy.

## ConsoleCommand classes
Those classes are UI layer of this application

## PersonService class
This is to perform business logic.

## Database
This app uses H2 file based Database.
It will be initiated automatically if DB file does not exist.   

# Build & Run
`./gradew build` for Mac & linux or `gradlew.bat build` for windows

`java -jar build/libs/wonlee_accela-0.0.1-SNAPSHOT.jar`
