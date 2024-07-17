package org.example.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "sanyouConsumer", topic = "sanyouDelayTaskTopic")
public class SanYouDelayTaskTopicListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.printf("获取到延迟任务:{}", msg);
    }

}
