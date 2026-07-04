# Step 1: Use an official Java runtime image as a base
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the compiled JAR file from the target folder into the container
COPY target/*.jar app.jar

# Step 4: Expose port 8080 so we can access the API
EXPOSE 8080

# Step 5: Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]