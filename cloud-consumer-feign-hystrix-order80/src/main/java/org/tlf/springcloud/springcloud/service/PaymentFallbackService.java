package org.tlf.springcloud.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback paymentinfo ok";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "fallback paymentinfo timeout";
    }
}
