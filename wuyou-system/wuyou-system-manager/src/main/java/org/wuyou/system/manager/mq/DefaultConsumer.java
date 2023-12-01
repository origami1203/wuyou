package org.wuyou.system.manager.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * @author origami
 * @date 2023/10/23 16:55
 */
@Component
@Slf4j
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "test", consumerGroup = "test-consumer")
public class DefaultConsumer implements RocketMQListener<String> {
    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String message) {
        // do something
    }
}
