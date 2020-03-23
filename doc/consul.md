## Consul部署方案

### 1.客户端

```text
consul agent -dev
监听8500端口
```

### 2.服务提供者

jar包

```xml
 <!--SpringCloud consul-server-->         
<dependency>             
    <groupId>org.springframework.cloud</groupId>           
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>       
</dependency>
```

`application.xml`

```yml
server :   
  # consul 服务端口   
  port :  8006
spring :  
  application :    
    name : cloud-provider-payment   
cloud :     
  consul :    
  # consul 注册中心地址    
    host : localhost       
    port :  8500       
    discovery :          
      hostname : 127.0.0.1
      service-name: ${spring.application.name}
```

主启动类加`@EnableDiscoveryClient`注解



### 3.服务消费者

jar包、`application.yml`配置及主启动类配置同前。

```text
1.Eureka: AP
2.Consul: CP
3.Zookeeper: CP
```
[参考](https://blog.csdn.net/qq_41211642/article/details/104836741)