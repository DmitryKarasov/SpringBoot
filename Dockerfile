FROM openjdk:17-alpine

EXPOSE 8081

COPY target/spring_boot-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","myapp.jar"]