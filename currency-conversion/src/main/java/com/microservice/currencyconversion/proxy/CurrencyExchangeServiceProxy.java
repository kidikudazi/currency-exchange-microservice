package com.microservice.currencyconversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;

import com.microservice.currencyconversion.bean.CurrencyConversionBean;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="forex-service")
@RibbonClient(name="forex-service")
@RequestMapping("/api/v1")
public interface CurrencyExchangeServiceProxy {
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyConversionBean retrieveExchangeValue
    (@PathVariable("from") String from, @PathVariable("to") String to);
}