## eureka与springcloud整合部署方案

### 1.单机版

#### 1.1.创建Eureka-server

1.`pom`文件引入jar包

```xml
<dependencies>     
    <dependency>         
        <groupId> org.springframework.cloud </groupId>         
        <artifactId> spring-cloud-starter-netflix-eureka-server </artifactId>     
    </dependency> 
</dependencies>
```

2.`application.yml`配置

```yml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
# 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
# 这里可以自己配置
      defaultZone: http://eureka7002.com:7002/eureka/ 
```

3.主启动类配置：

```java
package org.tlf.springcloud.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // 这个注解一定要加！！！
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
```

#### 1.2.创建Eureka-Client

jar包同前，`application.yml`配置如下：

```yml
eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka
#      defaultZone: http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka # 集群版
  instance:
    instance-id: payment8001
```

主启动类中要加入`@EnableEurekaClient`注解。

#### 1.3.启动逻辑

先启动`EurekaServer`，再启动`EurekaClient`。



### 2.集群版

保证微服务高可用。原理：部署多个节点互相ping通即可，`application.yml`配置如下：

```yml
eureka:
  instance:
    hostname: eureka7002.com #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
	# 表示依赖eureka7003.com这个实例
      defaultZone: http://eureka7003.com:7003/eureka/
```

`EurekaClient application.yml`中也要更新集群版`defaultZone`配置

```yml
eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      # 集群版
      defaultZone: http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka 
```

y

### 3.负载均衡

如果使用的是`RestTemplate`作为发送HTTP请求的工具类，可以在`ApplicationContextConfig`中配置负载均衡注解：

```java
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    // applicationContext.xml <bean id="" class=""></bean>
    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

...
// 具体方法类似这种
@GetMapping("/consumer/payment/create")
public CommonResult<Payment> create(Payment payment){
    return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);}
```



### 4.服务发现

在主启动类上加上`@EnableDiscoveryClient`，再在实现类中引入`DiscoveryClient`：

```java
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();  //得到所有的微服务
        for (String element : services) {
            log.info("*****element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICES"); //得到一个具体微服务的所有实例
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
```



### 5.自我保护

保护模式主要用于一组客户端和`Eureka Server`之间存在网络分区场景下的保护，一旦进入保护模式。

`Eureka Server`将会尝试保护器服务注册表的信息，不再删除服务注册表中的数据，即不会注销任何微服务。

这是属于`AP`概念。

可以禁用

`eureka.server.enable-self-preservation=false`

心跳机制也可以通过参数进行修改：

```text
eureka.instance.lease-renewal-interval-in-seconds=30
eureka.instance.lease-expiration-duration-seconds=90
```

