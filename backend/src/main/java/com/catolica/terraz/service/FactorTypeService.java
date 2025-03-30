package com.catolica.terraz.service;

import com.catolica.terraz.repository.FactorTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FactorTypeService {

    private final FactorTypeRepository factorTypeRepository;

    public List<FactorTypeDTO> getAllFactorTypes() {

    }
}
