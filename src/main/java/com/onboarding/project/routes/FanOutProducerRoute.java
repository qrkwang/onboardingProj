package com.onboarding.project.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class FanOutProducerRoute extends RouteBuilder{
    @Value("${fanout.exchange}")
    private String TEST_EXCHANGE;

    private String properties = "&setSkipExchangeDeclare=true&arg.queue.x-queue-type=quorum&autoDelete=false";

    @Override
    public void configure() throws Exception {
        from("timer://dataFanOut?fixedRate=true&delay=0&period=2s")
                .routeId("FanOut Route")
                .transform().constant("Hello World")
                .to("rabbitmq:" + TEST_EXCHANGE + "?exchangeType=fanout&skipQueueDeclare=true&skipQueueBind=true" + properties);

//        from("timer://dataFanOut?fixedRate=true&delay=0&period=2s")
//                .transform().constant("Hello World")
//                .to("rabbitmq:" + TEST_EXCHANGE + "?exchangeType=fanout&skipQueueDeclare=true&skipQueueBind=true");

    }

}
