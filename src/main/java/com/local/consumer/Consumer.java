package com.local.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        /*-----Creating connectionFactory for ActiveMQ-----------*/
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("ankan","ankan","tcp://localhost:61616");

        /*-----------Creating connection for the queue---------------*/
        Connection connection=connectionFactory.createConnection();
        connection.start();

        /*-----------creating session ------------------*/
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue("Local_Queue");

        MessageConsumer consumer=session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}