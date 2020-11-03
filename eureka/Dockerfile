FROM alpine:3.9

ADD target/*.jar eureka.jar

#声明端口
EXPOSE 8761

#运行
ENTRYPOINT ["java", "-jar", "eureka,jar"]