package com.example.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.currency.entity.CurrencyEntity;
import com.example.currency.service.CurrencyService;


@RestController
//@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public Double convert(@RequestParam("amount") double value,
    @RequestParam("from") String from,
    @RequestParam("to") String to)
    {
        return currencyService.convertCurrency(from, to, value);
        
    }
    
    @GetMapping("/{name}")
    public CurrencyEntity getCurrencyDetailsByName(@PathVariable String name)
    {
        return currencyService.getAllExchangeRatesForSpecific(name);
    }

    @GetMapping("/All")
    public CurrencyEntity getDefaultCurrencyRates()
    {
        return currencyService.getAllExchangeRates();
    }
}
