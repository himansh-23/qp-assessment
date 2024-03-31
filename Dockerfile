FROM maven:3.8.3-openjdk-17
EXPOSE 8080
WORKDIR /app
COPY target/grocery-0.0.1-SNAPSHOT.jar /app/grocery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/app/grocery-0.0.1-SNAPSHOT.jar"]