FROM ubuntu:latest
LABEL authors="jake"
FROM openjdk:17-jdk-slim

# Install Gradle
RUN apt-get update && apt-get install -y curl unzip && \
    curl -s "https://get.sdkman.io" | bash && \
    bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install gradle"

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Ensure Gradle wrapper is executable
RUN chmod +x gradlew

# Run the application
CMD ["./gradlew", "bootRun"]
ENTRYPOINT ["top", "-b"]