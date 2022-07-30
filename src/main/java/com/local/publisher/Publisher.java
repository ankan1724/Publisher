package com.local.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


public class Publisher {
    public static void main(String[] args) throws JMSException {
        /*-----Creating connectionFactory for ActiveMQ-----------*/
        ConnectionFactory  connectionFactory=new ActiveMQConnectionFactory("ankan","ankan","tcp://localhost:61616");
        
        /*-----------Creating connection for the queue---------------*/
        Connection connection=connectionFactory.createConnection();
        connection.start();
        
        /*-----------creating session ------------------*/
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
        /*-----------creating destination / queue---------------*/
        Destination destination=session.createQueue("Local_Queue");
        
        /*---------creating message producer to produce message-----------*/
        MessageProducer producer=session.createProducer(destination);
         
        /*---------------creating test message to publish-------------*/
        TextMessage message=session.createTextMessage("First message published");
        
        /*--------------sending message to queue--------------*/
        producer.send(message);
       System.out.println("Message sent successfully");
        
        /*---------------------closing connection--------------*/
        session.close();;
        connection.close();
        
    }
}