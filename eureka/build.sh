#!/usr/bin/bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t registry.cn-beijing.aliyuncs.com/sc0915/eureka .

docker push registry.cn-beijing.aliyuncs.com/sc0915/eureka
