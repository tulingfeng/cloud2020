package org.tlf.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlf.springcloud.dao.OrderMapper;
import org.tlf.springcloud.entities.Order;

@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    public boolean insert(Order order) {
        // 1 新建订单
        log.info("----->开始新建订单");
        orderMapper.insert(order);

        // 2 扣减库存
        log.info("----->订单微服务开始调用库存,做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存,做扣减End");

        // 3 扣减账户
        log.info("----->订单微服务开始调用账户,做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户,做扣减End");

        // 4 修改订单状态,从0到1,1代表已完成
        log.info("----->修改订单状态开始");
        order.setStatus(0);
        orderMapper.insertSelective(order);

        log.info("----->下订单结束了,O(∩_∩)O哈哈~");

        return true;

    }


}
