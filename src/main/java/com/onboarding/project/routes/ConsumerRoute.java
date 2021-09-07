package com.onboarding.project.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {
    final private String TEST_EXCHANGE = "testExchange";
    final private String TEST_QUEUE = "testQueue";
    final private String TEST_ROUTING_KEY = "testRoutingKey";


    @Override
    public void configure() throws Exception {

//        from("rabbitmq:" + TEST_EXCHANGE + "?hostname=" + HOST_NAME  + "?portNumber" +
//                PORT_NUM + "?queue=" +TEST_QUEUE + "?routingKey=" + TEST_ROUTING_KEY)
//                .log("Received message - ${body}");
//        from("rabbitmq:amq.direct?queue" = TEST_QUEUE + "?hostname=" + HOST_NAME + "?portNumber" + PORT_NUM);
        from("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" + TEST_ROUTING_KEY + "&autoDelete=false")
                .log("Received message - ${body}");
//        from ("rabbitmq:amq.direct?queue=TEST_QUEUE&routingKey=TEST_ROUTING_KEY&autoDelete=false&hostname=localhost&portNumber=5672&username=guest&password=guest")
//                .log("Received message - ${body}");

    }

}
