package com.catolica.terraz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeighborhoodDTO {
  private Long id;
  private String name;
  private PriceFactorDTO priceFactor;
}
