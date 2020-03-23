## Gateway部署方案

1.`pom.xml`中增加：

```xml
<!--gateway-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

2.`application.xml`配置如下：

```xml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway

  cloud:
    gateway:  # gateway配置
      discovery:
        locator:
          enabled: true   #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #payment_routh    #路由的ID，没有固定规则但要求唯一，简易配合服务名
          uri: lb://cloud-provider-services        #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**          #断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_routh   #路由的ID，没有固定规则但要求唯一，简易配合服务名
          uri: lb://cloud-provider-services          #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/discovery/**         #断言，路径相匹配的进行路由

eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka
```



3.主启动类

```java
@SpringBootApplication
@EnableEurekaClient  // 还是要配置Eureka客户端注解
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class,args);
    }
}
```

