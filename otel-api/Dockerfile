FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q dependency:go-offline
COPY src ./src
RUN mvn -B -q package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/otel-api-0.0.1-SNAPSHOT.jar app.jar
ENV OTEL_JAVA_AGENT_VERSION=1.33.0
# Download the OpenTelemetry Java auto-instrumentation agent (public).
RUN set -eu \
 && curl -Lf "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_JAVA_AGENT_VERSION}/opentelemetry-javaagent.jar" \
      -o /opt/otel-javaagent.jar
EXPOSE 8080
ENTRYPOINT ["java","-javaagent:/opt/otel-javaagent.jar","-jar","/app/app.jar"]
