package com.catolica.terraz.service;

import com.catolica.terraz.dto.NeighborhoodDTO;
import com.catolica.terraz.dto.PriceFactorDTO;
import com.catolica.terraz.model.Neighborhood;
import com.catolica.terraz.model.PriceFactor;
import com.catolica.terraz.repository.NeighborhoodRepository;
import com.catolica.terraz.repository.PriceFactorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NeighborhoodService {

  private final NeighborhoodRepository neighborhoodRepository;
  private final PriceFactorRepository priceFactorRepository;
  private final ModelMapper modelMapper;

  public List<NeighborhoodDTO> getAllNeighborhoods() {
    return neighborhoodRepository.findAll().stream()
        .map(
            nb -> {
              NeighborhoodDTO dto = modelMapper.map(nb, NeighborhoodDTO.class);
              if (nb.getPriceFactor() != null) {
                dto.setPriceFactor(modelMapper.map(nb.getPriceFactor(), PriceFactorDTO.class));
              }
              return dto;
            })
        .collect(Collectors.toList());
  }

  public NeighborhoodDTO getNeighborhoodById(Long id) {
    Neighborhood nb =
        neighborhoodRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Neighborhood not found, id=" + id));
    NeighborhoodDTO dto = modelMapper.map(nb, NeighborhoodDTO.class);
    if (nb.getPriceFactor() != null) {
      dto.setPriceFactor(modelMapper.map(nb.getPriceFactor(), PriceFactorDTO.class));
    }
    return dto;
  }

  public NeighborhoodDTO createNeighborhood(NeighborhoodDTO dto) {
    Long pfId = dto.getPriceFactor().getId();
    PriceFactor pf =
        priceFactorRepository
            .findById(pfId)
            .orElseThrow(() -> new RuntimeException("PriceFactor not found, id=" + pfId));

    Neighborhood nb = Neighborhood.builder().name(dto.getName()).priceFactor(pf).build();

    Neighborhood saved = neighborhoodRepository.save(nb);
    NeighborhoodDTO result = modelMapper.map(saved, NeighborhoodDTO.class);
    result.setPriceFactor(modelMapper.map(saved.getPriceFactor(), PriceFactorDTO.class));
    return result;
  }

  public NeighborhoodDTO updateNeighborhood(Long id, NeighborhoodDTO dto) {
    Neighborhood existing =
        neighborhoodRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Neighborhood not found, id=" + id));

    existing.setName(dto.getName());

    Long newPfId = dto.getPriceFactor().getId();
    if (!newPfId.equals(existing.getPriceFactor().getId())) {
      PriceFactor pf2 =
          priceFactorRepository
              .findById(newPfId)
              .orElseThrow(() -> new RuntimeException("PriceFactor not found, id=" + newPfId));
      existing.setPriceFactor(pf2);
    }

    Neighborhood saved = neighborhoodRepository.save(existing);
    NeighborhoodDTO result = modelMapper.map(saved, NeighborhoodDTO.class);
    result.setPriceFactor(modelMapper.map(saved.getPriceFactor(), PriceFactorDTO.class));
    return result;
  }

  public void deleteNeighborhood(Long id) {
    neighborhoodRepository.deleteById(id);
  }
}
