package com.paaro.traderdemo.persistence.repository;

import com.paaro.traderdemo.persistence.model.Trader;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface TraderRepository extends PanacheRepository<Trader> {
}
