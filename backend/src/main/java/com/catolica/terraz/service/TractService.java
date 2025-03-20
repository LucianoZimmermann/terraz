package com.catolica.terraz.service;

import com.catolica.terraz.dto.TractDTO;
import com.catolica.terraz.model.Tract;
import com.catolica.terraz.repository.TractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TractService {

    private final TractRepository tractRepository;

    public TractDTO saveTract(TractDTO tractDTO) {
        Tract tract = toEntity(tractDTO);
        tractRepository.save(tract);
        return toDTO(tract);
    }

    public List<TractDTO> getAllTracts() {
        return tractRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TractDTO toDTO(Tract tract) {
        return TractDTO.builder()
                .id(tract.getId())
                .squareMeters(tract.getSquareMeters())
                .address(tract.getAddress())
                .build();
    }

    public Tract toEntity(TractDTO tractDTO) {
        return Tract.builder()
                .id(tractDTO.getId())
                .squareMeters(tractDTO.getSquareMeters())
                .address(tractDTO.getAddress())
                .build();
    }
}
