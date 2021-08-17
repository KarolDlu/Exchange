package com.karold.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private BigDecimal bid;

    private BigDecimal ask;

    public Rate() {
    }

    public Rate(BigDecimal bid, BigDecimal ask) {
        this.bid = bid;
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }
}
