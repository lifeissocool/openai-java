FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

ENV OPENAI_API_KEY=你的API_KEY

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","/app/target/openai-1.0.0.war","--spring.profiles.active=prod"]
