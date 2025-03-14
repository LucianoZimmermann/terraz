package com.catolica.terraz.service;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.model.Factor;
import com.catolica.terraz.repository.FactorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactorService {

    private final FactorRepository factorRepository;

    public List<FactorDTO> getAllFactors() {
        List<Factor> factors = factorRepository.findAll();
        return factors.stream().map(FactorDTO::fromEntity).collect(Collectors.toList());
    }
}
