FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# JAR will be mapped from local project
ENTRYPOINT ["java", "-jar", "/app/target/transaction.jar"]
