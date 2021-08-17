package com.karold.currencyexchange.service;


import com.karold.currencyexchange.model.Currency;
import com.karold.currencyexchange.model.CurrencyCode;
import com.karold.currencyexchange.model.CurrencyPricesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeService {

    private NbpApiConnectionService connectionService;

    @Autowired
    public ExchangeService(NbpApiConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public Currency exchange(CurrencyCode from, CurrencyCode to, BigDecimal value) {
        if (!from.equals(to)) {
            if (from.equals(CurrencyCode.PLN)) {
                return fromPlnTo(to, value);
            }
            if (to.equals(CurrencyCode.PLN)) {
                return toPlnFrom(from, value);
            }
            else {
                Currency plnValue = toPlnFrom(from, value);
                Currency result = fromPlnTo(to, plnValue.getValue());
                return result;
            }
        }
        return new Currency(to, value);
    }

    private Currency fromPlnTo(CurrencyCode currencyCode, BigDecimal value) {
        CurrencyPricesResponse prices = connectionService.getPricesForCurrency(currencyCode);
        BigDecimal result = value.divide(prices.getAsk(), 2, RoundingMode.HALF_UP);
        return new Currency(prices.getCode(), result);
    }

    private Currency toPlnFrom(CurrencyCode currencyCode, BigDecimal value) {
        CurrencyPricesResponse prices = connectionService.getPricesForCurrency(currencyCode);
        BigDecimal result = value.multiply(prices.getBid()).setScale(2, RoundingMode.HALF_UP);
        return new Currency(CurrencyCode.PLN, result);
    }
}
