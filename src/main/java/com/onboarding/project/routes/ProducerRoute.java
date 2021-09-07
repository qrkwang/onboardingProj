package com.onboarding.project.routes;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRoute extends RouteBuilder {
    @Value("${proj.exchange}")
    private String TEST_EXCHANGE;

    @Value("${proj.queue}")
    private String TEST_QUEUE;

    @Value("${proj.routingkey}")
    private String TEST_ROUTING_KEY;

    @Override
    public void configure() throws Exception {
        from("timer:producer-route?fixedRate=true&period=3s")
                .transform().constant("Hello World")
                .to("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" +
                        TEST_ROUTING_KEY + "&autoDelete=false");



//        from("direct:startQueuePoint").id("idOfQueueHere").marshal(jsonDataFormat)
//                .to("rabbitmq://localhost:5672/javainuse.exchange?queue=javainuse.queue&autoDelete=false").end();
    }
}
