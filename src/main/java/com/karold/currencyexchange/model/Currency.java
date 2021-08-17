package com.karold.currencyexchange.model;

import java.math.BigDecimal;

public class Currency {

    private CurrencyCode currencyCode;

    private BigDecimal value;

    public Currency(CurrencyCode currencyCode, BigDecimal value) {
        this.currencyCode = currencyCode;
        this.value = value;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getValue() {
        return value;
    }
}
