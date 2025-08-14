#
# 빌드(Build) 스테이지
#
# Gradle 빌드를 위한 JDK 21 기반 이미지를 사용합니다.
FROM gradle:8.8-jdk21-jammy AS builder

# 컨테이너 내 작업 디렉토리를 설정합니다.
WORKDIR /app

# 호스트의 프로젝트 파일들을 컨테이너 내부로 복사합니다.
# 이렇게 하면 빌드 환경이 컨테이너 내부에 격리됩니다.
COPY . .

# Gradle Wrapper를 사용하여 애플리케이션을 빌드합니다.
# 이 단계에서 JAR 파일이 생성됩니다.
RUN ./gradlew build --no-daemon

#
# 실행(Run) 스테이지
#
# 경량화된 OpenJDK JRE 21 이미지를 사용합니다.
# 빌드에 필요한 도구(Gradle) 없이, 실행에 필요한 JRE만 포함하여 이미지 크기를 줄입니다.
FROM eclipse-temurin:21-jre-jammy

# 컨테이너 내 작업 디렉토리를 설정합니다.
WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일을 복사합니다.
# 'Daejeon'은 프로젝트 이름, '0.0.1-SNAPSHOT'은 버전입니다.
# `ls build/libs` 명령으로 정확한 파일명을 확인하여 수정할 수 있습니다.
COPY --from=builder /app/build/libs/Daejeon-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 실행 시 사용할 환경 변수를 설정합니다.
# 'docker' 프로파일을 활성화하여 application-docker.yml을 사용하도록 합니다.
ENV SPRING_PROFILES_ACTIVE=docker

# 8081번 포트를 노출합니다.
EXPOSE 8081

# 컨테이너가 시작될 때 애플리케이션을 실행하는 명령어입니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
