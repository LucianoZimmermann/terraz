package com.catolica.terraz.dto;

import com.catolica.terraz.enums.FactorType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactorDTO {
    private Long id;
    private Long thirdPartyId;
    private Float materialCost;
    private Float laborCost;
    private FactorType factorType;
}
