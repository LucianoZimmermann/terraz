package com.catolica.terraz.model;

import jakarta.persistence.*;
import lombok.*;

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

  private Double factor;
}
