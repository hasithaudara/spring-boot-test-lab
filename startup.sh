#!/bin/sh
profile_name=$1
mvn clean package
java -jar target/SpringBootPracticeLab-0.0.1-SNAPSHOT.jar --spring.profiles.active=$profile_name