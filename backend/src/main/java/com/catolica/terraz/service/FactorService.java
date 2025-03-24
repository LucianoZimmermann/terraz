package com.catolica.terraz.service;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.model.Factor;
import com.catolica.terraz.model.ThirdParty;
import com.catolica.terraz.repository.FactorRepository;
import com.catolica.terraz.repository.ThirdPartyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactorService {

    private final FactorRepository factorRepository;
    private final ThirdPartyRepository thirdPartyRepository;
    private final ModelMapper modelMapper;

    public FactorDTO saveFactor(FactorDTO factorDTO) {
        Factor factor = modelMapper.map(factorDTO, Factor.class);

        ThirdParty thirdParty = thirdPartyRepository.findById(factorDTO.getThirdPartyId())
                .orElseThrow(() -> new RuntimeException("Third Party Not Found"));
        factor.setThirdParty(thirdParty);

        Factor savedFactor = factorRepository.save(factor);

        FactorDTO savedFactorDTO = modelMapper.map(savedFactor, FactorDTO.class);
        if (savedFactor.getThirdParty() != null) {
            savedFactorDTO.setThirdPartyId(savedFactor.getThirdParty().getId());
        }
        return savedFactorDTO;
    }

    public List<FactorDTO> getAllFactors() {
        return factorRepository.findAll().stream().map(factor -> {
            FactorDTO factorDTO = modelMapper.map(factor, FactorDTO.class);
            if (factor.getThirdParty() != null) {
                factorDTO.setThirdPartyId(factor.getThirdParty().getId());
            }
            return factorDTO;
        }).collect(Collectors.toList());
    }
}
