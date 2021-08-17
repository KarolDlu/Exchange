package com.karold.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karold.currencyexchange.model.CurrencyCode;
import com.karold.currencyexchange.model.CurrencyPricesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class NbpApiConnectionService {

    private HttpClient httpClient;

    private final static String NBP_API_URI = "http://api.nbp.pl/api/";

    @Autowired
    public NbpApiConnectionService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CurrencyPricesResponse getPricesForCurrency(CurrencyCode currencyCode) {
        CurrencyPricesResponse prices = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(NBP_API_URI +"exchangerates/rates/c/" + currencyCode + "/")).GET().build();
            HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            prices = objectMapper.readValue(response.body().toString(), CurrencyPricesResponse.class);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return prices;
    }


}
