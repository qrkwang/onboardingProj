//package com.onboarding.project.routes;
//import org.apache.camel.builder.RouteBuilder;
//
//public class ProducerRoute extends RouteBuilder {
//    final private String TEST_EXCHANGE = "testExchange";
//    final private String TEST_QUEUE = "testQueue";
//    final private String TEST_ROUTING_KEY = "testRoutingKey";
//    final private String HOST_NAME = "localhost";
//    final private String PORT_NUM = "5672";
//
//    @Override
//    public void configure() throws Exception {
//        from("rabbitmq:" + TEST_EXCHANGE + "?hostname=" + HOST_NAME  + "?portNumber" +
//                PORT_NUM + "?queue=" +TEST_QUEUE + "?routingKey=" + TEST_ROUTING_KEY + "?autoDelete=false")
//                .to("Received message - ${body}");
//
//
////        from("direct:startQueuePoint").id("idOfQueueHere").marshal(jsonDataFormat)
////                .to("rabbitmq://localhost:5672/javainuse.exchange?queue=javainuse.queue&autoDelete=false").end();
//    }
//}
