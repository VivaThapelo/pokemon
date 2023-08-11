# Use a base image with Java 17 installed
FROM maven:3.8.3-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY ./src ./src

# Build the application
RUN mvn package

# Expose the port on which the application will run
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/backend-1.0-SNAPSHOT.jar"]
