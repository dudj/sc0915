20200915学习springCloud

目录介绍


/src/main/java/com/ld/product/

-------|  client 调用外部接口

-------|  config 配置

-------|  constant 常量

-------|  controller 控制器

-------|  converter 数据转化

-------|  dataobject 数据对象

-------|  dto 数据传输对象

-------|  enums 使用到类别的枚举值

-------|  exception 异常处理

-------|  repostiory dao 数据库连接

-------|  service 业务逻辑层

-------|  utils 工具包

-------|  verify 参数验证

-------|  VO view object 视图对象(返回给外部的)

20200925 业务代码构造，订单和商品服务


tagExplain.md 各个tag标签升级的代码含义


注意：windows和linux下的大小写区别


java项目一些命令
打包(跳过测试类，-U重新下载)：mvn clean package -Dmaven.test.skip=true -U
根据active进行启动：java -jar -Dspring.profile.active=xxx target/*.jar
linux下服务之间的依赖：

mvn clean package -Dmaven.test.skip=true

mvn install:install-file -DgroupId=com.ld -DartifactId=product-client -Dpackaging=jar -Dfile=./target/product-client-0.0.1-SNAPSHOT.jar

