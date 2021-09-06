package com.onboarding.project;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class ProducerRoute extends RouteBuilder {
    final private String TEST_EXCHANGE = "";
    final private String TEST_QUEUE = "";
    final private String TEST_ROUTING_KEY = "";

    @Override
    public void configure() throws Exception {
        from("rabbitmq://localhost:5671/" + TEST_EXCHANGE + "?queue=" +TEST_QUEUE)
                .log("Received message - ${body}");


//        from("direct:startQueuePoint").id("idOfQueueHere").marshal(jsonDataFormat)
//                .to("rabbitmq://localhost:5672/javainuse.exchange?queue=javainuse.queue&autoDelete=false").end();
    }
}
