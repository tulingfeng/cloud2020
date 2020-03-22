package org.tlf.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlf.springcloud.entities.CommonResult;
import org.tlf.springcloud.entities.Order;
import org.tlf.springcloud.service.OrderService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("order/create")
    public CommonResult create(Order order) {
        Boolean result = orderService.insert(order);
        if(result){
            log.info("插入成功");
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else{
            log.info("插入失败");
            return new CommonResult(444,"插入数据失败",null);
        }

    }
}
