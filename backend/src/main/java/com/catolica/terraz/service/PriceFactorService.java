package com.catolica.terraz.service;

import com.catolica.terraz.dto.PriceFactorDTO;
import com.catolica.terraz.model.PriceFactor;
import com.catolica.terraz.repository.PriceFactorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceFactorService {

  private final PriceFactorRepository priceFactorRepository;
  private final ModelMapper modelMapper;

  public PriceFactorDTO savePriceFactor(PriceFactorDTO priceFactorDTO) {
    PriceFactor priceFactor = modelMapper.map(priceFactorDTO, PriceFactor.class);
    PriceFactor savedPriceFactor = priceFactorRepository.save(priceFactor);
    return modelMapper.map(savedPriceFactor, PriceFactorDTO.class);
  }

  public List<PriceFactorDTO> getAllPriceFactors() {
    return priceFactorRepository.findAll().stream()
        .map(priceFactor -> modelMapper.map(priceFactor, PriceFactorDTO.class))
        .collect(Collectors.toList());
  }

  public Optional<PriceFactorDTO> getPriceFactorById(Long id) {
    return priceFactorRepository
        .findById(id)
        .map(priceFactor -> modelMapper.map(priceFactor, PriceFactorDTO.class));
  }
}
