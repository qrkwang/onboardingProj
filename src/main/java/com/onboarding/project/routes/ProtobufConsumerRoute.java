package com.onboarding.project.routes;

import com.onboarding.project.bean.DeviceBean;
import com.onboarding.project.protobuf.Device;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
public class ProtobufConsumerRoute extends RouteBuilder {
    @Value("${proj.exchange}")
    private String TEST_EXCHANGE;

    @Value("${proj.queue}")
    private String TEST_QUEUE;

    @Value("${proj.routingkey}")
    private String TEST_ROUTING_KEY;


    @Override
    public void configure() throws Exception {

        from("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" + TEST_ROUTING_KEY + "&autoDelete=false")
                .routeId("Protobuf Route")
                .process(e->{
                    //Receive protobuf, remove headers, get byte array from data -> parse data then push to processDeviceReading method in DeviceBean.

                    e.getMessage().removeHeaders("*");
                    byte[] data = e.getMessage().getBody(byte[].class);
                    Device.DeviceReading incomingDeviceReading = Device.DeviceReading.parseFrom(data);
                    DeviceBean.getInstance().processDeviceReading(incomingDeviceReading);
                });

        //OTHER CODES FOR FUTURE REFERENCE:

//        from("rabbitmq:amq.direct?queue="+ TEST_QUEUE + "&routingKey=" + TEST_ROUTING_KEY + "&autoDelete=false")
//                .routeId("Consumer Route")
//                .process(e->{
//                    e.getMessage().removeHeaders("*");
//                    byte[] data = e.getMessage().getBody(byte[].class);
//                    Device.DeviceReading incomingDeviceReading = Device.DeviceReading.parseFrom(data);
//                    DeviceBean.getInstance().processDeviceReading(incomingDeviceReading);
//                })              .log("Received message - ${body}");

        //        from("rabbitmq:" + TEST_EXCHANGE + "?hostname=" + HOST_NAME  + "?portNumber" +
//                PORT_NUM + "?queue=" +TEST_QUEUE + "?routingKey=" + TEST_ROUTING_KEY)
//                .log("Received message - ${body}");
//        from("rabbitmq:amq.direct?queue" = TEST_QUEUE + "?hostname=" + HOST_NAME + "?portNumber" + PORT_NUM);

//        from ("rabbitmq:amq.direct?queue=TEST_QUEUE&routingKey=TEST_ROUTING_KEY&autoDelete=false&hostname=localhost&portNumber=5672&username=guest&password=guest")
//                .log("Received message - ${body}");

//        from("rabbitmq:amq.direct?queue=" + TEST_QUEUE + "&routingKey=" + TEST_ROUTING_KEY + "&autoDelete=false")
//                .log("Received message - ${body}");


    }

}
