## OpenFeign部署方案

### 1.启动配置

jar包

```xml
<dependency>             
    <groupId>org.springframework.cloud</groupId>    
    <artifactId>spring-cloud-starter-openfeign</artifactId>     
</dependency>
```

`application.yml`配置(`Eureka Server`配置)：

```yml
server :   
  port :  80
eureka :   
  client :     
    register-with-eureka :  false     
    fetch-registry :  true     
    service-url :        
      defaultZone : http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka 
```



### 2.服务配置

主启动类加上`@EnableFeignClients`注解。

服务接口加上`@FeignClient`注解。

```java
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICES") // 一定要加
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
}
```

