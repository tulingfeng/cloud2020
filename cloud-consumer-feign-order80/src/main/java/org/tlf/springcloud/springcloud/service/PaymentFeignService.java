package org.tlf.springcloud.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tlf.springcloud.springcloud.entities.CommonResult;
import org.tlf.springcloud.springcloud.entities.Payment;

@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICES")
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
