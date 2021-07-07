package cn.nimoblog.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @title: MessageSender
 * @Author Chuf
 * @Date: 2021/7/6 4:19 下午
 * @Version 1.0
 * 使用JMS方式发送接收消息 - 消息发送者
 */
public class MessageSender {

    // tcp 地址
    public static final String BROKER_URL = "tcp://localhost:61616";
    // 发送次数
    public static final int SEND_NUM = 5;
    // 目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp
    public static final String DESTINATION = "sagedragon.mq.queue";

    public static void sendMessage(Session session, MessageProducer producer) throws Exception {

//        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < SEND_NUM; i++) {
            String message = "发送消息第" + (i + 1) + "条";
            TextMessage text = session.createTextMessage(message);

            System.out.println(message);
            producer.send(text);
        }
    }

    public static void run() throws Exception {
        Connection connection = null;
        Session session = null;
        try {
            //创建连接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(DESTINATION);
            // 创建消息制作者
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, producer);
            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        MessageSender.run();
    }
}
