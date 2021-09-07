package com.onboarding.project.routes;

import com.google.protobuf.ByteString;
import com.onboarding.project.bean.DeviceBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.onboarding.project.protobuf.DeviceOuterClass;


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
//        from("timer:producer-route?fixedRate=true&period=10s")
//                .transform().constant("Hello World")
//        // use of Spring Bean to transform the message
//        // transform is used when the body of the Message is changed
//                .to("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" +
//                        TEST_ROUTING_KEY + "&autoDelete=false");


        from("timer:producer-route?fixedRate=true&period=2s")
                .routeId("Route")// send to consumer route
                .bean(DeviceBean.getInstance(), "packDeviceReadingProto")
                .to("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" +
                        TEST_ROUTING_KEY + "&autoDelete=false");

//        from("direct:startQueuePoint").id("idOfQueueHere").marshal(jsonDataFormat)
//                .to("rabbitmq://localhost:5672/javainuse.exchange?queue=javainuse.queue&autoDelete=false").end();
    }
}
