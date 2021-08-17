package com.karold.currencyexchange;

import com.karold.currencyexchange.model.Currency;
import com.karold.currencyexchange.model.CurrencyCode;
import com.karold.currencyexchange.model.CurrencyPricesResponse;
import com.karold.currencyexchange.model.Rate;
import com.karold.currencyexchange.service.ExchangeService;
import com.karold.currencyexchange.service.NbpApiConnectionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeServiceTest {

    @Mock
    private NbpApiConnectionService apiService;

    @InjectMocks
    private ExchangeService exchangeService;

    @Test
    public void shouldReturn_Currency_Where_ExchangedValue_And_CurrencyCodeEqualsCAD() {
        List<Rate> usdRates = new ArrayList<>();
        usdRates.add(new Rate(BigDecimal.valueOf(3.8497), BigDecimal.valueOf(3.9275)));
        when(apiService.getPricesForCurrency(CurrencyCode.USD)).thenReturn(new CurrencyPricesResponse("dolar amerykański", CurrencyCode.USD, usdRates));
        List<Rate> cadRates = new ArrayList<>();
        cadRates.add(new Rate(BigDecimal.valueOf(3.0734), BigDecimal.valueOf(3.1354)));
        when(apiService.getPricesForCurrency(CurrencyCode.CAD)).thenReturn(new CurrencyPricesResponse("dolar kanadyjski", CurrencyCode.CAD, cadRates));
        Currency result = exchangeService.exchange(CurrencyCode.USD, CurrencyCode.CAD, new BigDecimal(100));
        Assert.assertEquals(BigDecimal.valueOf(122.78), result.getValue());
        Assert.assertEquals(CurrencyCode.CAD, result.getCurrencyCode());
    }

    @Test
    public void shouldReturn_Currency_Where_ExchangedValue_And_CurrencyCodeEqualsPLN() {
        List<Rate> usdRates = new ArrayList<>();
        usdRates.add(new Rate(BigDecimal.valueOf(3.8497), BigDecimal.valueOf(3.9275)));
        when(apiService.getPricesForCurrency(CurrencyCode.USD)).thenReturn(new CurrencyPricesResponse("dolar amerykański", CurrencyCode.USD, usdRates));
        Currency result = exchangeService.exchange(CurrencyCode.USD, CurrencyCode.PLN, BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(384.97).setScale(2, RoundingMode.HALF_UP), result.getValue());
        Assert.assertEquals(CurrencyCode.PLN, result.getCurrencyCode());

    }
}
