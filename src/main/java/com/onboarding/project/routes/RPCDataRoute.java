package com.onboarding.project.routes;

import com.onboarding.project.bean.DeviceBean;
import com.onboarding.project.protobuf.Device;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class RPCDataRoute extends RouteBuilder {

    @Value("${rpc.exchange}")
    private String exchange;

    @Value("${rpc.queue}")
    private String queue;

    @Value("${rpc.routingkey}")
    private String routingkey;

    @Value("${rpc.cqueue}")
    private String cqueue;

    @Value("${rpc.properties}")
    private String properties;

    @Override
    public void configure() throws Exception {


        //Consumer send request (to change reading to 789) protobuf to "queue" with replyto to "cqueue"
        //Set headers to use hard coded reply to and correlation ID.
        from("timer:producer-route?fixedRate=true&period=5s")
                .routeId("Route")// send to consumer route
                .bean(DeviceBean.getInstance(), "packRequestProto")
                .setHeader(RabbitMQConstants.REPLY_TO, constant("TEST_REPLY_TO_INIT"))
                .setHeader(RabbitMQConstants.CORRELATIONID, constant(12345))
                .to("rabbitmq:amq.direct?queue=" + queue + "&routingKey=" +
                        routingkey + properties);

        //Producer read request from "queue", set devicereading to 789, reply back to ReplyTo channel with reply protobuf.
        from("rabbitmq:amq.direct?queue=" + queue + "&routingKey=" +
                routingkey + properties)
                .setHeader(RabbitMQConstants.CORRELATIONID, constant(12345))
                .process(e->{
                    e.getMessage().removeHeaders("*");
                    byte[] data = e.getMessage().getBody(byte[].class);
                    Device.request incomingRequest = Device.request.parseFrom(data);
                    DeviceBean.getInstance().processRequest(incomingRequest);
                })
                .bean(DeviceBean.getInstance(), "packReplyProto")
                .to("rabbitmq:amq.direct?queue=" + cqueue + "&routingKey=" +
                        routingkey + properties);

        //Read the replyToChannel
        from("rabbitmq:amq.direct?queue=" + cqueue + "&routingKey=" +
                routingkey + properties)
                .setHeader(RabbitMQConstants.CORRELATIONID, constant(12345))
                .process(e->{
                    e.getMessage().removeHeaders("*");
                    byte[] data = e.getMessage().getBody(byte[].class);
                    Device.reply incomingReply = Device.reply.parseFrom(data);
                    DeviceBean.getInstance().processReply(incomingReply);
                });

    }
}
