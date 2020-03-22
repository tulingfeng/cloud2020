package org.tlf.springcloud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tlf.springcloud.domain.CommonResult;
import org.tlf.springcloud.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author zzyy
 * @date 2020/3/8 14:21
 **/
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping(value = "account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
