package cn.nimoblog.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.MapMessage;
import java.util.Date;

/**
 * @title: SpringSender
 * @Author Chuf
 * @Date: 2021/7/6 8:15 下午
 * @Version 1.0
 * Spring JMSTemplate 消息发送者
 */
public class SpringSender {
    public static void main(String[] args) {

        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");

        jmsTemplate.send(session -> {
            MapMessage message = session.createMapMessage();
            message.setString("message", "current system time: " + new Date().getTime());
            System.out.println(message);
            return message;
        });
    }
}
