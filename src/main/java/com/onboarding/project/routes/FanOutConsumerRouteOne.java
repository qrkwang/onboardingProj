package com.onboarding.project.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FanOutConsumerRouteOne extends RouteBuilder {
    @Value("${fanout.exchange}")
    private String TEST_EXCHANGE;

    //First queue binded to fanout exchange
    private String TEST_QUEUE = "testFanOutqueue1";

    @Override
    public void configure() throws Exception {
                from("rabbitmq:" + TEST_EXCHANGE + "?queue=" + TEST_QUEUE)
                        .routeId("FanOut Route")
                        .log("Received message - ${body}");
    }
}
