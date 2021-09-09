package com.onboarding.project.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class RPCControlRoute extends RouteBuilder {

    @Value("${control.exchange}")
    private String exchange;

    @Value("${control.queue}")
    private String queue;

    @Value("${control.routingkey}")
    private String routingkey;

    @Value("${control.cqueue}")
    private String cqueue;

    @Value("${control.properties}")
    private String properties;

    @Override
    public void configure() throws Exception {
        from("rabbitmq:amq.direct?queue=" + queue + "&routingKey=" +
                routingkey + properties);
    }
}
