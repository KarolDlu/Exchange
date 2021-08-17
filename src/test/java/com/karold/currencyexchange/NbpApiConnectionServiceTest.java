package com.karold.currencyexchange;

import com.karold.currencyexchange.model.CurrencyCode;
import com.karold.currencyexchange.model.CurrencyPricesResponse;
import com.karold.currencyexchange.service.NbpApiConnectionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;


public class NbpApiConnectionServiceTest {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private NbpApiConnectionService apiService = new NbpApiConnectionService(httpClient);

    @Test
    public void shouldReturn_CurrencyPriceResponse_When_NbpApiIsAccessible() {
        Assert.assertEquals(CurrencyPricesResponse.class, apiService.getPricesForCurrency(CurrencyCode.USD).getClass());
    }


    @Test
    public void shouldReturn_CurrencyPriceResponse_Where_CurrencyCodeEqualsUSD() {
        CurrencyPricesResponse result = apiService.getPricesForCurrency(CurrencyCode.USD);
        Assert.assertEquals(CurrencyCode.USD, result.getCode());
    }
}
