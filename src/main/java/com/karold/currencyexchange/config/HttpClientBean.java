package com.karold.currencyexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class HttpClientBean {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
