FROM maven:3.8.4 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/polisportiva-micronaut-0.1.jar polisportiva-micronaut-0.1.jar
COPY src/main/resources/application.yml application.yml

CMD ["java", "-jar", "polisportiva-micronaut-0.1.jar", "--micronaut.config.files=application.yml"]

#FROM maven:3.8.4 as builder
#
#WORKDIR /app
#
#COPY pom.xml .
#COPY src ./src
#RUN mvn package
#
#FROM openjdk:17
#
#WORKDIR /app
#
#COPY --from=builder /app/target/polisportiva-micronaut-0.1.jar polisportiva-micronaut-0.1.jar
#
#CMD ["java", "-jar", "polisportiva-micronaut-0.1.jar"]
