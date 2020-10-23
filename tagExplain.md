tag标签解释
本地通过 git tag 展示所有标签

git show name 展示具体某一个

v0.1 eureka服务端和客户端以及商品服务初始化

v0.2 商品和订单服务构造，之间互调

v0.3 商品和订单服务改造成多模块，需要注意的是两者之间互调以及启动来加注解，各个公共模块之间的调用

v0.4 将所有服务的版本调整，并且开发了配置中心，将订单服务进行了配置

v0.5 使用SpringCloud Stream 操作消息队列、RabbitMQ使用之spring-boot-starter-amqp

v0.6 使用mq进行商品和订单之间 扣库存的逻辑 使用到json互相转换数据和数据存储到redis等