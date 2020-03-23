## Zuul部署方案

1.`pom.xml`配置增加：

```xml
 < dependency > 
     < groupId > org.springframework.cloud </ groupId > 
     < artifactId > spring-cloud-starter-eureka </ artifactId > 
 </ dependency > 
 < dependency > 
    < groupId > org.springframework.cloud </ groupId > 
    < artifactId > spring-cloud-starter-zuul </ artifactId > 
 </ dependency > 
```

2.`application.yml`中需要绑定`Eureka`

```xml
server:   
   port:  9527 
  
spring:   
   application: 
     name:  microservicecloud-zuul-gateway 
  
eureka:   
   client:   
     service-url:   
       defaultZone:  http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka   
   instance: 
     instance-id:  gateway-9527.com 
     prefer-ip-address:   true   
```



3.主启动类加`@EnableZuulProxy`注解

