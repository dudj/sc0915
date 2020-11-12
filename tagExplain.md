tag标签解释
本地通过 git tag 展示所有标签

git show name 展示具体某一个

v0.1 eureka服务端和客户端以及商品服务初始化

v0.2 商品和订单服务构造，之间互调

v0.3 商品和订单服务改造成多模块，需要注意的是两者之间互调以及启动来加注解，各个公共模块之间的调用

v0.4 将所有服务的版本调整，并且开发了配置中心，将订单服务进行了配置

v0.5 使用SpringCloud Stream 操作消息队列、RabbitMQ使用之spring-boot-starter-amqp

v0.6 使用mq进行商品和订单之间 扣库存的逻辑 使用到json互相转换数据和数据存储到redis等

v0.7 zuul 前置和post过滤器包括限流使用，模拟买卖家登录存放数据，完成订单完结接口

v0.8 zuul权限校验，跨域等

v0.9 hystrix 熔断 降级 包括错误时候 界面查看，以及feignHystrix的使用，超时配置等

v1.0 rancher进行部署eureka和config，eureka的高可用

v1.1 rancher部署产品和订单服务，以及网关的高可用等

v1.2 各个服务升级完成

v1.3 eureka-springboot集成graylog，测试

v1.4 swagger-ui配置全局的接口界面，微服务即为多个服务，那不可能每一 个服务都对外提供一个文档，有了zuul网关，我们可以通过网关，进行各个服务之间的配置 以api-gateway(zuul网关服务)、config(配置中心)、product(产品服务)、user(用户服务)作为swagger多服务配置的包 order服务单独进行了配置