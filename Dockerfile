# JDK 21을 사용하는 경량화된 OpenJDK 이미지를 사용합니다.
FROM eclipse-temurin:21-jdk-jammy

# 빌드된 jar 파일을 컨테이너 내부에 복사합니다.
# 'build/libs/Daejeon-0.0.1-SNAPSHOT.jar'는 실제 jar 파일 경로에 맞게 수정해야 합니다.
COPY build/libs/Daejeon-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 실행 시 사용할 환경 변수를 설정합니다.
# SPRING_PROFILES_ACTIVE를 'docker'로 설정하여 application-docker.yml을 사용하도록 합니다.
ENV SPRING_PROFILES_ACTIVE=docker

# 컨테이너가 시작될 때 애플리케이션을 실행하는 명령어입니다.
ENTRYPOINT ["java", "-jar", "/app.jar"]
