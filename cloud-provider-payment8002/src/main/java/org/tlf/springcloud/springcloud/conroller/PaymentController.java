package org.tlf.springcloud.springcloud.conroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tlf.springcloud.springcloud.entities.CommonResult;
import org.tlf.springcloud.springcloud.entities.Payment;
import org.tlf.springcloud.springcloud.service.PaymentService;

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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

}
