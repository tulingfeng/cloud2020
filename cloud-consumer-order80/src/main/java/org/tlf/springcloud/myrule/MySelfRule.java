package org.tlf.springcloud.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MySelfRule {
    @Bean
    public IRule nyRule() {
        log.info("随机轮询...");
        return new RandomRule();
    }
}
