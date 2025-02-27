FROM openjdk:11-jre-slim
COPY target/hello-world-ui-1.0-SNAPSHOT.jar hello-world-ui.jar
CMD ["java", "-jar", "hello-world-ui.jar"]