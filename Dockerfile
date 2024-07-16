FROM gradle:jdk21-jammy

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

CMD ["java", "-jar", "build/libs/training-docker-spring-0.0.1-SNAPSHOT.jar"]

EXPOSE 8888