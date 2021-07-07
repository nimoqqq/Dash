package cn.nimoblog.activemq.springbootMq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @title: MessageQueue
 * @Author Chuf
 * @Date: 2021/7/7 2:29 下午
 * @Version 1.0
 * 队列消息发送者
 */
@Component
public class MessageQueue {

    //返回一个名为my-message的队列,并且注册为bean
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("my-message");
    }

}
