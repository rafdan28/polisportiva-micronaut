FROM maven:3.8.4 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn package

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/polisportiva-micronaut-0.1.jar polisportiva-micronaut-0.1.jar

CMD ["java", "-jar", "polisportiva-micronaut-0.1.jar"]
