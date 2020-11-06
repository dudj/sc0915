FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
