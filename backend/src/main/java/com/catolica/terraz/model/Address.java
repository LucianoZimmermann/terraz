package com.catolica.terraz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private String neighborhood;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "price_factor_id")
    private PriceFactor priceFactor;
}
