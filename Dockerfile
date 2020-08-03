FROM openjdk:latest
LABEL maintainer="Renan Del Puppo Furtado"
COPY ./target/*.jar /
EXPOSE 35000
CMD "java" "-jar" "trustly-git-connector-service-1.1.0-SNAPSHOT.jar"