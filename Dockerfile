FROM openjdk:16-slim
LABEL maintainer="Renan Del Puppo Furtado"
COPY ./target/*.jar /usr/src/app/
WORKDIR /usr/src/app
EXPOSE 35000
CMD "java" "-jar" "trustly-git-connector-service-1.1.0-SNAPSHOT.jar"