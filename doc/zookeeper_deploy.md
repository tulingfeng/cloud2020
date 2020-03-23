## zookeeper与springcloud整合部署方案

### 1.关闭防火墙

```text
systemctl stop firewalld
systemctl status firewalld
```

### 2.服务提供者

jar包依赖：

```xml
<dependency>            
    <groupId>org.springframework.cloud</groupId>         
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>         
</dependency> 
```

`application.yml`配置：

```yml
server :    
  # 8004 表示注册到 zookeeper 服务器的支付服务提供者端口号  
  port :  8004
  
spring :  
  application :    
    #  服务别名 --- 注册 zookeeper 到注册中心的名称      
    name : cloud-provider-payment   
cloud:    
  zookeeper:     
  #  默认 localhost:2181      
    connect-string: localhost:2181 
```

### 3.服务消费者

jar包及`application.yml`配置同前。

主启动类加上`@EnableDiscoveryClient`注解。

