FROM hub.c.163.com/library/java:8-alpine

ADD server/target/*.jar user.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/user.jar"]
