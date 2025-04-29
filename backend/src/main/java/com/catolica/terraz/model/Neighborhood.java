package com.catolica.terraz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "neighborhoods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Neighborhood {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "price_factor_id")
  private PriceFactor priceFactor;
}
