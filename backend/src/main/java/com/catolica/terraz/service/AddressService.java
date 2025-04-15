package com.catolica.terraz.service;

import com.catolica.terraz.dto.AddressDTO;
import com.catolica.terraz.model.Address;
import com.catolica.terraz.repository.AddressRepository;
import com.catolica.terraz.repository.PriceFactorRepository;
import com.catolica.terraz.repository.TractRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;
  private final TractRepository tractRepository;
  private final PriceFactorRepository priceFactorRepository;
  private final ModelMapper modelMapper;

  public List<AddressDTO> getAllAddresses() {
    return addressRepository.findAll().stream()
        .map(
            address -> {
              AddressDTO dto = modelMapper.map(address, AddressDTO.class);
              dto.setPriceFactorId(
                  address.getPriceFactor() != null ? address.getPriceFactor().getId() : null);
              return dto;
            })
        .collect(Collectors.toList());
  }

  public AddressDTO saveAddress(AddressDTO addressDTO) {
    Address address = modelMapper.map(addressDTO, Address.class);
    address.setPriceFactor(
        priceFactorRepository
            .findById(addressDTO.getPriceFactorId())
            .orElseThrow(() -> new RuntimeException("PriceFactor not found")));
    Address savedAddress = addressRepository.save(address);
    AddressDTO savedDTO = modelMapper.map(savedAddress, AddressDTO.class);
    savedDTO.setPriceFactorId(
        savedAddress.getPriceFactor() != null ? savedAddress.getPriceFactor().getId() : null);
    return savedDTO;
  }

  public AddressDTO updateAddress(AddressDTO addressDTO) {
    Address existing =
        addressRepository
            .findById(addressDTO.getId())
            .orElseThrow(() -> new RuntimeException("Address not found"));

    modelMapper.map(addressDTO, existing);

    existing.setPriceFactor(
        priceFactorRepository
            .findById(addressDTO.getPriceFactorId())
            .orElseThrow(() -> new RuntimeException("PriceFactor not found")));

    Address updated = addressRepository.save(existing);

    AddressDTO updatedDTO = modelMapper.map(updated, AddressDTO.class);
    updatedDTO.setPriceFactorId(
        updated.getPriceFactor() != null ? updated.getPriceFactor().getId() : null);

    return updatedDTO;
  }

  public void deleteAddress(Long id) {
    addressRepository.deleteById(id);
  }
}
