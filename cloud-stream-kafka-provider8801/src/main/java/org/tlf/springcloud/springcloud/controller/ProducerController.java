package org.tlf.springcloud.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlf.springcloud.springcloud.services.ISendService;

import javax.annotation.Resource;

@RestController
public class ProducerController {

    @Resource
    private ISendService sendService;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return sendService.send();
    }

}
