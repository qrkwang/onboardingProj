package com.onboarding.project.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.onboarding.project.protobuf.Device;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DeviceBean {
    private static Logger log = LoggerFactory.getLogger(DeviceBean.class);
    private static DeviceBean instance = null;

    //Can store in instance and use later.
    private String replyTo;
    private String correlationID;


    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    public synchronized static DeviceBean getInstance() {
        if (instance == null) {
            instance = new DeviceBean();

//


        }
        return instance;
    }

    public void processDeviceReading(Device.DeviceReading incDeviceReading){
        //get the inc device reading
        log.info("in processdevicereading");

        long reading = incDeviceReading.getDeviceReading();
        long deviceDatetime = incDeviceReading.getDeviceDatetime();
        String deviceId = incDeviceReading.getDeviceID();

        log.info(reading + " " + deviceDatetime + " " + deviceId);


        //They take incoming one and store in their own hash map, while above is just a print
//        long currentTimestamp = incHeartbeat.getTimestamp();
//        Map<String, HeartbeatProto.ConnectionStatus> connectionMapping = incHeartbeat.getConnectionsMap();
//        for (services service: services.values()){
//            String serviceString = service.toString();
//            if (connectionMapping.containsKey(serviceString)){
//                latestHeartbeats.get(service).put("timestamp", currentTimestamp);
//                latestHeartbeats.get(service).put("connected", connectionMapping.get(serviceString).equals(HeartbeatProto.ConnectionStatus.CONNECTED));
//            }
//        }
    }

    public void processRequest(Device.request incRequest){
        log.info("in processrequestmethod");
        log.info("printing received request");
        //get the inc device reading
        String requestId = incRequest.getRequestID();
        Device.request.RequestType requestType =incRequest.getRequestType();
        long deviceDateTime = incRequest.getDeviceDatetime();
        Device.DeviceReading dr = incRequest.getDeviceReading();

        //Supposed to change device reading record here, but just printing
        log.info(requestId + " " + requestType.getValueDescriptor() + " " + deviceDateTime + " " + dr.getDeviceReading());

        System.out.println("processing request, changing record details");
    }

    public void processReply(Device.reply incReply){
        log.info("in processreply method");
        log.info("printing received reply");
        //get the inc device reading
        String requestId = incReply.getRequestID();
        Device.DeviceReading dr = incReply.getDeviceReading();
        int status = (int) incReply.getStatus();

        log.info(requestId + " " + dr.getDeviceReading() + " " + status);
    }

    public byte[] packDeviceReadingProto(){
        log.info("inside function to pack device reading");
        Device.DeviceReading.Builder deviceBuilder = Device.DeviceReading.newBuilder();
        deviceBuilder.setDeviceReading(70);
        deviceBuilder.setDeviceID("1005");
        deviceBuilder.setDeviceDatetime(210709);
        deviceBuilder.build();
        return deviceBuilder.build().toByteArray();
    }

    public byte[] packRequestProto(){
        log.info("inside function to pack request proto");
        Device.DeviceReading.Builder deviceBuilder = Device.DeviceReading.newBuilder();
        deviceBuilder.setDeviceReading(789);
        deviceBuilder.setDeviceID("1005");
        deviceBuilder.setDeviceDatetime(210709);
        deviceBuilder.build();

        Device.request.Builder requestBuilder = Device.request.newBuilder();
        requestBuilder.setRequestID("1");
        requestBuilder.setRequestType(Device.request.RequestType.INITIALIZATION);
        requestBuilder.setDeviceDatetime(210908);
        requestBuilder.setDeviceID(1);
        requestBuilder.setDeviceReading(deviceBuilder);

        return requestBuilder.build().toByteArray();
    }


        public byte[] packReplyProto(){
            log.info("inside function to pack reply proto");
            Device.reply.Builder replyBuilder = Device.reply.newBuilder();

            //They stored the readings globally, but I just recreate to send back
            Device.DeviceReading.Builder deviceBuilder = Device.DeviceReading.newBuilder();
            deviceBuilder.setDeviceReading(789);
            deviceBuilder.setDeviceID("1005");
            deviceBuilder.setDeviceDatetime(210709);
            deviceBuilder.build();

            replyBuilder.setRequestID("1");
            replyBuilder.setDeviceReading(deviceBuilder);
            replyBuilder.setStatus(5);
            replyBuilder.build();


            return replyBuilder.build().toByteArray();
        }

}
