package com.example.currency.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.currency.entity.CurrencyEntity;

@Service
public class CurrencyService {

    RestTemplate restTemplate = new RestTemplate();
    String urlTemplate = "https://open.er-api.com/v6/latest/INR";

    public CurrencyEntity getAllExchangeRates()
    {
        return restTemplate.getForObject(urlTemplate, CurrencyEntity.class);
    }

    public CurrencyEntity getAllExchangeRatesForSpecific(String from)
    {
        String newUrl = urlTemplate.replace("INR", from);
        return restTemplate.getForObject(newUrl, CurrencyEntity.class);
    }

    public Double convertCurrency(String from, String to, Double amount)
    {
        CurrencyEntity entity = getAllExchangeRatesForSpecific(from);
        double toRate = entity.getRates().getOrDefault(to, 1d);
        double fromRate = entity.getRates().getOrDefault(from, 1d);
        return amount * (toRate / fromRate);
    }

 
    
}
