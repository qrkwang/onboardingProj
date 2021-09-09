package com.onboarding.project.routes;

import com.google.protobuf.ByteString;
import com.onboarding.project.bean.DeviceBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.onboarding.project.protobuf.DeviceOuterClass;


//@Component
public class ProtobufProducerRoute extends RouteBuilder {
    @Value("${proj.exchange}")
    private String TEST_EXCHANGE;

    @Value("${proj.queue}")
    private String TEST_QUEUE;

    @Value("${proj.routingkey}")
    private String TEST_ROUTING_KEY;

    @Override
    public void configure() throws Exception {


        from("timer:producer-route?fixedRate=true&period=2s") //Timer
                .routeId("Protobuf Route")// send to consumer route
                .bean(DeviceBean.getInstance(), "packDeviceReadingProto") //Call method in bean to pack protobuf file, then send this file through queue.
                .to("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" +
                        TEST_ROUTING_KEY + "&autoDelete=false");

    }
}
