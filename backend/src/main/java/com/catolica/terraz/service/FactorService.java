package com.catolica.terraz.service;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.model.Factor;
import com.catolica.terraz.model.ThirdParty;
import com.catolica.terraz.repository.FactorRepository;
import com.catolica.terraz.repository.ThirdPartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactorService {

    private final FactorRepository factorRepository;
    private final ThirdPartyRepository thirdPartyRepository;

    public FactorDTO saveFactor(FactorDTO factorDTO) {
        Factor newFactor = this.toEntity(factorDTO);
        factorRepository.save(newFactor);
        return this.fromEntity(newFactor);
    }

    public List<FactorDTO> getAllFactors() {
        List<Factor> factors = factorRepository.findAll();
        return factors.stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public FactorDTO fromEntity(Factor factor) {
        return FactorDTO.builder()
                .id(factor.getId())
                .thirdPartyId(factor.getThirdParty().getId())
                .materialCost(factor.getMaterialCost())
                .laborCost(factor.getLaborCost())
                .factorType(factor.getFactorType())
                .build();
    }

    public Factor toEntity(FactorDTO factorDTO) {
        ThirdParty thirdParty = thirdPartyRepository.findById(factorDTO.getThirdPartyId())
                .orElseThrow(() -> new RuntimeException("Third Party Not Found"));

        return Factor.builder()
                .id(factorDTO.getId())
                .thirdParty(thirdParty)
                .materialCost(factorDTO.getMaterialCost())
                .laborCost(factorDTO.getLaborCost())
                .factorType(factorDTO.getFactorType())
                .build();
    }

}
