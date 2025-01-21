# Этап сборки (builder)
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean
RUN mvn package install

# Этап запуска (runner)
FROM tomcat:9.0-jre11-temurin-focal

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]