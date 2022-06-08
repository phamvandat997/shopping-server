FROM openjdk:8-jre
FROM maven
EXPOSE 8081
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean install -DskipTest
COPY ./target/shop-server-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "-Dspring.profiles.active=local", "shop-server-0.0.1-SNAPSHOT.jar"]