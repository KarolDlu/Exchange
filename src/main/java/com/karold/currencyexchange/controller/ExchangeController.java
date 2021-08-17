package com.karold.currencyexchange.controller;

import com.karold.currencyexchange.model.Currency;
import com.karold.currencyexchange.model.CurrencyCode;
import com.karold.currencyexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    private ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/exchange")
    public ResponseEntity<Currency> exchangeCurrency(@RequestParam CurrencyCode from, @RequestParam CurrencyCode to, @RequestParam BigDecimal value) {
        return new ResponseEntity<>(exchangeService.exchange(from, to, value), HttpStatus.OK);
    }
}
