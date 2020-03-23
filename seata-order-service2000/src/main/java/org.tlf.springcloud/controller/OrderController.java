package org.tlf.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlf.springcloud.domain.CommonResult;
import org.tlf.springcloud.domain.Order;
import org.tlf.springcloud.service.OrderService;

import javax.annotation.Resource;

/**
 * @author zzyy
 * @date 2020/3/8 14:21
 **/
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}
