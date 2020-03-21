package org.tlf.springcloud.springcloud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlf.springcloud.springcloud.dao.PaymentMapper;
import org.tlf.springcloud.springcloud.entities.Payment;


@Service
public class PaymentService{

    @Autowired
    private PaymentMapper paymentMapper;

    public boolean insert(Payment payment) {

        return 1==paymentMapper.insert(payment);

    }


    public Payment getPaymentById(Long id) {

        return paymentMapper.selectByPrimaryKey(id);
    }






}
