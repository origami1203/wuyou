package org.wuyou.system.manager.mq;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author origami
 * @date 2023/10/23 16:28
 */
@RequiredArgsConstructor
@Component
public class DefaultProducer {
    private final RocketMQTemplate rocketMQTemplate;

    public void sendMsg(String msg) {
        rocketMQTemplate.send("test", MessageBuilder.withPayload(msg).build());
    }
}
