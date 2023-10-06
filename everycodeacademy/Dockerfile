FROM openjdk:17-jdk-slim
WORKDIR /app

# 스카우터 agent.java를 컨테이너 내부로 복사
COPY scouter/agent.java /app/scouter/agent.java
COPY build/libs/everycodeacademy-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-javaagent:scouter/agent.java/scouter.agent.jar" ,"-jar", "everycodeacademy-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "everycodeacademy-0.0.1-SNAPSHOT.jar"]

