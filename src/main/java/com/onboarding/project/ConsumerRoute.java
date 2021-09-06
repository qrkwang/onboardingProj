package com.onboarding.project;

import org.apache.camel.builder.RouteBuilder;

public class ConsumerRoute extends RouteBuilder {

    final private String TEST_EXCHANGE = "";
    final private String TEST_QUEUE = "";
    final private String TEST_ROUTING_KEY = "";
    final private String HOST_NAME = "";
    final private String PORT_NUM = "";
    @Override
    public void configure() throws Exception {
        from("rabbitmq:" + TEST_EXCHANGE + "?hostname=" + HOST_NAME  + "?portNumber" +
                PORT_NUM + "?queue=" +TEST_QUEUE + "?routingKey=" + TEST_ROUTING_KEY)
                        .log("Received message - ${body}");

    }

}
