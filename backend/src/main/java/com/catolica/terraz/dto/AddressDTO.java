package com.catolica.terraz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
  private Long id;
  private String street;
  private String city;
  private String neighborhood;
  private String cep;
  private Long priceFactorId;
}
