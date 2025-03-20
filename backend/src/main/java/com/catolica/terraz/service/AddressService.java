package com.catolica.terraz.service;

import com.catolica.terraz.dto.AddressDTO;
import com.catolica.terraz.model.Address;
import com.catolica.terraz.repository.AddressRepository;
import com.catolica.terraz.repository.PriceFactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final PriceFactorRepository priceFactorRepository;

    public List<AddressDTO> getAllAddresses() {
        List<AddressDTO> addresses = addressRepository.findAll().stream().map(this::toDTO).toList();
        return addresses;
    }

    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address newAddress = toEntity(addressDTO);
        addressRepository.save(newAddress);
        return toDTO(newAddress);
    }

    public AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .cep(address.getCep())
                .priceFactorId(address.getPriceFactor() != null ? address.getPriceFactor().getId() : null)
                .build();
    }


    public Address toEntity(AddressDTO addressDTO) {
        return Address.builder()
                .id(addressDTO.getId())
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .neighborhood(addressDTO.getNeighborhood())
                .cep(addressDTO.getCep())
                .priceFactor(priceFactorRepository.findById(addressDTO.getPriceFactorId())
                        .orElseThrow(() -> new RuntimeException("PriceFactor not found")))
                .build();
    }
}
