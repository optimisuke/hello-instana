FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q dependency:go-offline
COPY src ./src
RUN mvn -B -q package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/health-api-0.0.1-SNAPSHOT.jar app.jar
# Download the Instana Java agent from Maven Central (public, no auth required).
RUN set -eu \
 && curl -Lf "https://repo1.maven.org/maven2/com/instana/instana-javaagent/1.0.0/instana-javaagent-1.0.0.jar" \
      -o /opt/instana-agent.jar
EXPOSE 8080
ENTRYPOINT ["java","-javaagent:/opt/instana-agent.jar","-jar","/app/app.jar"]
