package org.tlf.springcloud.springcloud.services;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.util.UUID;

//这不是传统的service,这是和rabbitmq打交道的，不需要加注解@Service
//这里不掉dao，掉消息中间件的service
//信道channel和exchange绑定在一起
@EnableBinding(Source.class)    //定义消息的推送管道
public class SendService implements ISendService {
    @Resource
    private MessageChannel output;
    //官网就是这么写的，可以去官网看案例
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: " +serial);
        return null;
    }
}
