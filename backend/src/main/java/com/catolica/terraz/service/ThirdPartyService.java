package com.catolica.terraz.service;

import com.catolica.terraz.dto.ThirdPartyDTO;
import com.catolica.terraz.model.ThirdParty;
import com.catolica.terraz.repository.ThirdPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThirdPartyService {
    private final ThirdPartyRepository thirdPartyRepository;

    public ThirdPartyDTO saveThirdParty(ThirdPartyDTO thirdPartyDTO) {
        ThirdParty newThirdParty = toEntity(thirdPartyDTO);
        thirdPartyRepository.save(newThirdParty);
        return toDTO(newThirdParty);
    }

    public List<ThirdPartyDTO> getAllThirdParty() {
        List<ThirdParty> thirdParties = thirdPartyRepository.findAll();
        return thirdParties.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ThirdPartyDTO getThirdPartyById(Long id) {
        ThirdParty thirdParty = thirdPartyRepository.findById(id).orElseThrow(() -> new RuntimeException("Third party not found"));
        return toDTO(thirdParty);
    }

    public ThirdParty toEntity(ThirdPartyDTO thirdPartyDTO) {
        return ThirdParty.builder()
                .id(thirdPartyDTO.getId())
                .name(thirdPartyDTO.getName())
                .cnpj(thirdPartyDTO.getCnpj())
                .build();
    }

    public ThirdPartyDTO toDTO(ThirdParty thirdParty) {
        return ThirdPartyDTO.builder()
                .id(thirdParty.getId())
                .name(thirdParty.getName())
                .cnpj(thirdParty.getCnpj())
                .build();
    }
}
