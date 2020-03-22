package org.tlf.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.tlf.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---1",null);
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---2",null);
    }
}
