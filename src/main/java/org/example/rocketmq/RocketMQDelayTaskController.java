package org.example.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RocketMQDelayTaskController {

    @Resource
    private DefaultMQProducer producer;


    @GetMapping("/rocketmq/add")
    public void addTask(@RequestParam("task") String task) throws Exception {
        Message msg = new Message("sanyouDelayTaskTopic", "TagA", task.getBytes(RemotingHelper.DEFAULT_CHARSET));
        msg.setDelayTimeLevel(2);
        // 发送消息并得到消息的发送结果，然后打印
        System.out.println("提交延迟任务");
        producer.send(msg);
    }

}
