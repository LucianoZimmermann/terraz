package com.catolica.terraz.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "priceFactors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private Float factor;
}
