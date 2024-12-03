# Stage 1: Build the application
FROM ubuntu:22.04 AS build

WORKDIR /home/gradle/project/ptsd-apigateway

RUN apt-get update && apt-get install -y openjdk-17-jdk
RUN  apt-get update && apt-get install -y curl
RUN apt-get install -y unzip zip;
RUN apt-get clean;

COPY . .
RUN ls
RUN ./gradlew clean build -x test

# Stage 2: Run the application
FROM ubuntu:22.04

WORKDIR /root/workspace

RUN apt-get update && apt-get install -y openjdk-17-jdk
RUN apt-get clean;

COPY --from=build /home/gradle/project/ptsd-apigateway/build/libs/ptsd-apigateway-1.0.jar .

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "ptsd-apigateway-1.0.jar"]

EXPOSE 8000
