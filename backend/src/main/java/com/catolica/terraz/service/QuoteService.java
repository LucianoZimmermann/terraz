package com.catolica.terraz.service;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.dto.request.RequestQuoteDTO;
import com.catolica.terraz.dto.response.ResponseQuoteDTO;
import com.catolica.terraz.model.*;
import com.catolica.terraz.repository.*;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuoteService {

  private final QuoteRepository quoteRepository;
  private final FactorRepository factorRepository;
  private final TractRepository tractRepository;
  private final TractOwnerRepository tractOwnerRepository;
  private final AddressRepository addressRepository;
  private final FactorService factorService;
  private final ThirdPartyRepository thirdPartyRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public ResponseQuoteDTO saveQuote(RequestQuoteDTO quoteDTO) {
    Address address = modelMapper.map(quoteDTO.getTract().getAddress(), Address.class);
    Address savedAddress = addressRepository.saveAndFlush(address);

    TractOwner tractOwner = modelMapper.map(quoteDTO.getTractOwner(), TractOwner.class);
    TractOwner savedTractOwner = tractOwnerRepository.saveAndFlush(tractOwner);
    Tract tract = modelMapper.map(quoteDTO.getTract(), Tract.class);
    tract.setTractOwner(tractOwner);
    tract.setAddress(savedAddress);
    Tract savedTract = tractRepository.saveAndFlush(tract);

    Quote quote =
        Quote.builder()
            .tract(savedTract)
            .tractOwner(savedTractOwner)
            .factorList(new ArrayList<>())
            .totalPrice(0.0)
            .createDate(LocalDateTime.now())
            .build();
    Quote savedQuote = quoteRepository.saveAndFlush(quote);

    Quote finalSavedQuote = savedQuote;
    List<Factor> savedFactors =
        quoteDTO.getFactors().stream()
            .map(
                factorDTO -> {
                  Factor factor = modelMapper.map(factorDTO, Factor.class);
                  ThirdParty thirdParty =
                      thirdPartyRepository
                          .findById(factorDTO.getThirdPartyId())
                          .orElseThrow(
                              () ->
                                  new RuntimeException(
                                      "Third party not found with id: "
                                          + factorDTO.getThirdPartyId()));
                  factor.setThirdParty(thirdParty);
                  factor.setQuote(finalSavedQuote);
                  return factorRepository.saveAndFlush(factor);
                })
            .toList();

    Double totalPrice = factorService.calculateFactorsTotalPrice(quoteDTO.getFactors());
    savedQuote.getFactorList().addAll(savedFactors);
    savedQuote.setTotalPrice(totalPrice);
    savedQuote = quoteRepository.saveAndFlush(savedQuote);

    ResponseQuoteDTO responseDTO = modelMapper.map(savedQuote, ResponseQuoteDTO.class);
    List<FactorDTO> factorDTOList =
        savedQuote.getFactorList().stream()
            .map(factor -> modelMapper.map(factor, FactorDTO.class))
            .collect(Collectors.toList());
    responseDTO.setFactors(factorDTOList);
    return responseDTO;
  }

  public List<RequestQuoteDTO> getAllQuotes() {
    return quoteRepository.findAll().stream()
        .map(quote -> modelMapper.map(quote, RequestQuoteDTO.class))
        .collect(Collectors.toList());
  }

  public Optional<RequestQuoteDTO> getQuoteById(Long id) {
    return quoteRepository.findById(id).map(quote -> modelMapper.map(quote, RequestQuoteDTO.class));
  }

  public List<RequestQuoteDTO> getQuotesByOwnerId(Long id) {
    return quoteRepository.findAll().stream()
        .filter(quote -> quote.getTractOwner() != null && id.equals(quote.getTractOwner().getId()))
        .map(quote -> modelMapper.map(quote, RequestQuoteDTO.class))
        .collect(Collectors.toList());
  }
}
