package org.tlf.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlf.springcloud.domain.CommonResult;

import java.math.BigDecimal;

/**
 * @author zzyy
 * @date 2020/3/8 13:55
 **/
@FeignClient(value = "seata-account-service")
public interface AccountService {
    /**
     * 减余额
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping(value = "account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
