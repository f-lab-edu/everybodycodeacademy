FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/everycodeacademy-0.0.1-SNAPSHOT.jar /app/
COPY ./ /scouter/agent.java/conf/
CMD ["java", "-jar", "everycodeacademy-0.0.1-SNAPSHOT.jar"]
