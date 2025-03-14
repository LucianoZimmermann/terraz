package com.catolica.terraz.dto;

import com.catolica.terraz.enums.FactorEnum;
import com.catolica.terraz.model.Factor;
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
    private FactorEnum factorType;

    public static FactorDTO fromEntity(Factor factor) {
        return FactorDTO.builder()
                .id(factor.getId())
                .thirdPartyId(factor.getThirdParty().getId())
                .materialCost(factor.getMaterialCost())
                .laborCost(factor.getLaborCost())
                .factorType(factor.getFactorType())
                .build();
    }

    public Factor toEntity() {
        Factor factor = new Factor();
        factor.setId(id);
        factor.setMaterialCost(materialCost);
        factor.setLaborCost(laborCost);
        factor.setFactorType(factorType);
        return factor;
    }
}
