package com.catolica.terraz.model;

import com.catolica.terraz.enums.FactorTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "factor_types")
public class FactorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private FactorTypeEnum factorTypeEnum;
}
