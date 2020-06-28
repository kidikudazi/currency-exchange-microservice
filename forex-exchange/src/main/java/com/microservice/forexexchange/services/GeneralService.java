package com.microservice.forexexchange.services;

import com.microservice.forexexchange.models.ExchangeValue;
import com.microservice.forexexchange.repositories.ExchangeValueRepository;
import com.microservice.forexexchange.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {
    @Autowired
    private ExchangeValueRepository eValueRepository;

    // find currency exchange
    public ResponseEntity<ExchangeValue> getExchangeValue(String from, String to){
        
        ExchangeValue exchangeValue = eValueRepository.findByCurrencyFromAndCurrencyTo(from, to).orElse(null);
        
        return ResponseEntity.ok(exchangeValue);
    }
}