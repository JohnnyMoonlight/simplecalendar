FROM gradle:7.0.2-jdk11 AS build
COPY ./ /home/gradle/
WORKDIR /home/gradle/
RUN gradle build

FROM gcr.io/distroless/java:11
COPY --from=build /home/gradle/build/libs/simplecalendar-0.0.1-SNAPSHOT.jar /home/gradle/app/main.jar
WORKDIR /home/gradle/app/
CMD java -jar main.jar