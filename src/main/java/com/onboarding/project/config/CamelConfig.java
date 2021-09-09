package com.onboarding.project.config;
import com.onboarding.project.util.utilities;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLSocketFactory;

@Configuration
public class CamelConfig {

    @Value("${amqp.web.router.useSSL}")
    protected boolean webUseSSL;

    @Value("${amqp.web.router.user}")
    public String username;

    @Value("${amqp.web.router.password}")
    public String password;

    @Value("${amqp.web.router.host}")
    public String host;

    @Value("${amqp.web.router.port}")
    public Integer port;

    @Value("${amqp.web.router.useSSL}")
    protected boolean appUseSSL;

    @Value("${amqp.web.router.ssl_ca}")
    protected String caPathWeb;

    @Value("${amqp.web.router.ssl_cert}")
    protected String certPathWeb;

    @Value("${amqp.web.router.ssl_key}")
    protected String keyPathWeb;

    @Value("${amqp.web.router.ssl_keyPassword}")
    protected String keyPWWeb;



    @Bean
    public ConnectionFactory rabbitConnectionFactory(){
        Logger logger = LoggerFactory.getLogger(CamelConfig.class);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);

        if (appUseSSL) {
            try {
                SSLSocketFactory socketFactory = null;
                socketFactory = utilities.getSocketFactory(caPathWeb, certPathWeb, keyPathWeb, keyPWWeb);
                factory.setSocketFactory(socketFactory);

            } catch (Exception e) {
                logger.info(e.toString());
                logger.info("unable to setup rabbit mq client with ssl connection");
            }
        }

        return factory;

    }

}
