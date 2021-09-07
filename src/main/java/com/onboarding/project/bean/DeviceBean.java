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

    public synchronized static DeviceBean getInstance() {
        if (instance == null) {
            instance = new DeviceBean();

//


        }
        return instance;
    }

    public void processDeviceReading(Device.DeviceReading incDeviceReading){
        //get the inc device reading
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
    public byte[] packDeviceReadingProto(){
        log.info("inside function to pack");
        Device.DeviceReading.Builder deviceBuilder = Device.DeviceReading.newBuilder();
        deviceBuilder.setDeviceReading(70);
        deviceBuilder.setDeviceID("1005");
        deviceBuilder.setDeviceDatetime(210709);
        deviceBuilder.build();
        return deviceBuilder.build().toByteArray();
    }

}
