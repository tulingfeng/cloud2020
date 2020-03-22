package org.tlf.springcloud.springcloud.conroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;
import org.tlf.springcloud.entities.Payment;
import org.tlf.springcloud.springcloud.service.PaymentService;
import org.tlf.springcloud.entities.CommonResult;
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

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        Boolean result = paymentService.insert(payment);
        if(result){
            log.info("插入成功");
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else{
            log.info("插入失败");
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            log.info("查询成功");
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            log.info("查询失败");
            return new CommonResult(444,"没有对应记录,查询ID："+id,null);
        }
    }

    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
