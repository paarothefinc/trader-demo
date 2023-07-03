package com.paaro.traderdemo.persistence.model;

import com.paaro.traderdemo.dto.TaxationType;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRADER")
public class Trader {

    private Long id;
    private String name;
    private TaxationType taxationType;
    private BigDecimal value;

    public Trader() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    public TaxationType getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(final TaxationType taxationType) {
        this.taxationType = taxationType;
    }

    @Column(name = "taxation_value", nullable = false, precision = 8, scale = 2)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }
}
