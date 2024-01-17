FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY . .

# Build the Spring Boot application using Maven
RUN mvn dependency:resolve
RUN mvn clean package

FROM openjdk:17-oracle

# Copy the compiled Spring Boot JAR file into the container
COPY --from=build /app/target/reprizy.war /app/reprizy.war

COPY src/main/resources/lib /app/lib

RUN ls -al /app

# Expose the port your Spring Boot app is running on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-cp", "/app/reprizy.war:/app/lib/DAO.jar","ITU.Baovola.Gucci.GucciApplication"]