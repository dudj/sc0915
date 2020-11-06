FROM hub.c.163.com/library/java:8-alpine

ADD server/target/*.jar product.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/product.jar"]
