package com.main;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.main.utils.SSLUtils;

//Contrutor de configuración 
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SSLUtils.disableSslVerification();
        return new RestTemplate();
    }
}

