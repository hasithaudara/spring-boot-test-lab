FROM openjdk:17
LABEL maintainer="HasithaBombuwala"
ADD target/SpringBootPracticeLab-0.0.1-SNAPSHOT.jar spring-boot-practice-lab.jar
ENTRYPOINT ["java", "-jar", "spring-boot-practice-lab.jar"]