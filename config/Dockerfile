FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar config.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/config.jar"]
