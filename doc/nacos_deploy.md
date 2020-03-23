## Nacos部署方案

下载Nacos包，启动命令

```shell
sh start.sh
```



### 1.作为服务注册中心

1.`pom.xml`配置如下：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

2.服务提供者`application.yml`配置如下：

```yml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud: 
    nacos:  # nacos 配置ip
      discovery:
        server-addr: localhost:8848

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

消费者`application.yml`:

```yml
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

#消费者将要去访问的微服务名称（注册成功进nacos的微服务提供者），这里一定要注意！！！
service-url:
  nacos-user-service: http://nacos-payment-provider
```

3.主启动类还是`@EnableDiscoveryClient`注解

`Nacos`支持负载均衡，因为`nacos-discovery`包含有`netfix-ribbon`包。



### 2.作为服务配置中心

1.`pom.xml`配置如下：

```text\

```

```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

2.服务提供者`application.yml`配置如下：

```
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud: 
    nacos:  # nacos 配置ip
      discovery:
        server-addr: localhost:8848

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

消费者`application.yml`:

```
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

#消费者将要去访问的微服务名称（注册成功进nacos的微服务提供者），这里一定要注意！！！
service-url:
  nacos-user-service: http://nacos-payment-provider
```

3.主启动类还是`@EnableDiscoveryClient`注解

`Nacos`支持负载均衡，因为`nacos-discovery`包含有`netfix-ribbon`包。



### 2.作为服务配置中心

1.`pom.xml`配置如下：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

2.配置文件需要有2个：`bootstrap.yml`和`application.yml`:

`bootstrap.yml`

```yml
server:
  port: 3377
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # 注册中心
      config:
        server-addr: localhost:8848 # 配置中心
        file-extension: yml # 这里指定的文件格式需要和nacos上新建的配置文件后缀相同，否则读不到

# 对应nacos中的配置文件名：nacos-org.tlf.springcloud.config-client-dev.yml
```

`application.yml`

```yml
# application.yml 优先级低于bootstrap.yml
spring:
  profiles:
    active: dev # 开发环境
```



3.主启动类配置`@EnableDiscoveryClient`注解

业务类添加`@RefreshScop`，实现配置自动刷新。



### 3.持久化

将数据保存到mysql里面。

执行`nacos/conf`文件夹下的`nacos-mysql.sql`

更改`nacos/conf/application.properties`文件：

```text
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=123456
```







