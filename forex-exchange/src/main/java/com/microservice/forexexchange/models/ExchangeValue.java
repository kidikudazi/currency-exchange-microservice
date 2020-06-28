package com.microservice.forexexchange.models;

import lombok.*;

import java.math.BigDecimal;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "exchange_value")
public class ExchangeValue  extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "currency_to")
    private String currencyTo;

    @Column(name= "conversion_multiple")
    private BigDecimal conversionMultiple;
}