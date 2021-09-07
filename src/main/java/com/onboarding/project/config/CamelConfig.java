package com.onboarding.project.config;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class CamelConfig {
    final private String TEST_EXCHANGE = "testExchange";
    final private String TEST_QUEUE = "intern_testQueue";
    final private String TEST_ROUTING_KEY = "testRoutingKey";
    final private String HOST_NAME = "192.168.4.2";
    final private int PORT_NUM = 5671;

    public static final String RABBIT_URI = "rabbitmq:amq.direct?queue=%s&routingKey=%s&autoDelete=false";

    @Bean
    public ConnectionFactory rabbitConnectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        factory.setPort(PORT_NUM);
        factory.setUsername("user");
        factory.setPassword("JtcSubmit02!");
        return factory;

    }

}
