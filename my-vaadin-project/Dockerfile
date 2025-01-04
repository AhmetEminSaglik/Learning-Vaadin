FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/my-vaadin-project-1.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
