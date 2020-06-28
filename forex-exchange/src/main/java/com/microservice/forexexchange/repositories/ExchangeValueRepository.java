package com.microservice.forexexchange.repositories;

import java.util.Optional;

import com.microservice.forexexchange.models.ExchangeValue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
    Optional<ExchangeValue> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}