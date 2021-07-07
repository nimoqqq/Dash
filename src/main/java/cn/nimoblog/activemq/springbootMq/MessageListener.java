package cn.nimoblog.activemq.springbootMq;

//import com.dragon.activemq.demo.springboot.Constants;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Dragon
 * @version V0.1
 * @Title: com.dragon.activemq.demo.springboot.demo.MessageListener
 * @Description: 消息监听者
 * @date 2017/10/01 23:16
 */
@Component
public class MessageListener {

    /**
     * 使用@JmsListener注解来监听指定的某个队列内的消息,是否有新增,有的话则取出队列内消息
     * 进行处理
     **/
    @JmsListener(destination = "my-message")
    public void removeMessage(String msg) {
        //public void removeMessage(Email email) {
        System.out.println("监听接收到的消息是:" + msg);//打印队列内的消息
    }

    /*@JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }*/

}