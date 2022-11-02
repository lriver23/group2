FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} group2application.jar
ENTRYPOINT ["java","-jar","/group2application.jar"]
