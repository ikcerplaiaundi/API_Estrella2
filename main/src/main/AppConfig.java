package main.main;

import org.apache.tomcat.util.net.SSLUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import main.main.utils.SSLUtils;

//Contrutor de configuración 
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SSLUtils.disableSslVerification();
        return new RestTemplate();
    }
}

