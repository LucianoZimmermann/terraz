package com.catolica.terraz.repository;

import com.catolica.terraz.model.PriceFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceFactorRepository extends JpaRepository<PriceFactor, Long> {
}
