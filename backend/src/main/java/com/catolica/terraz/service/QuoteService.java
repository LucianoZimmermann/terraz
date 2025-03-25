package com.catolica.terraz.service;

import com.catolica.terraz.dto.RequestQuoteDTO;
import com.catolica.terraz.dto.ResponseQuoteDTO;
import com.catolica.terraz.model.*;
import com.catolica.terraz.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final FactorRepository factorRepository;
    private final TractRepository tractRepository;
    private final TractOwnerRepository tractOwnerRepository;
    private final AddressRepository addressRepository; // Repositório para Address
    private final FactorService factorService;
    private final ModelMapper modelMapper;

    @Transactional
    public ResponseQuoteDTO saveQuote(RequestQuoteDTO quoteDTO) {
        Address address = modelMapper.map(quoteDTO.getTract().getAddress(), Address.class);
        Address savedAddress = addressRepository.saveAndFlush(address);

        Tract tract = modelMapper.map(quoteDTO.getTract(), Tract.class);
        tract.setAddress(savedAddress);
        Tract savedTract = tractRepository.saveAndFlush(tract);

        TractOwner tractOwner = modelMapper.map(quoteDTO.getTractOwner(), TractOwner.class);
        TractOwner savedTractOwner = tractOwnerRepository.saveAndFlush(tractOwner);

        List<Factor> savedFactors = quoteDTO.getFactors().stream()
                .map(factorDTO -> factorRepository.saveAndFlush(modelMapper.map(factorDTO, Factor.class)))
                .collect(Collectors.toList());
        ResponseQuoteDTO responseDTO = modelMapper.map(quoteDTO, ResponseQuoteDTO.class);
        responseDTO.setTotalPrice(factorService.calculateFactorsTotalPrice(quoteDTO.getFactors()));
        responseDTO.setCreateDate(LocalDateTime.now());

        Quote quote = Quote.builder()
                .tract(savedTract)
                .tractOwner(savedTractOwner)
                .factorList(savedFactors)
                .totalPrice(responseDTO.getTotalPrice())
                .createDate(responseDTO.getCreateDate())
                .build();

        Quote savedQuote = quoteRepository.save(quote);

        return modelMapper.map(savedQuote, ResponseQuoteDTO.class);
    }

    public List<RequestQuoteDTO> getAllQuotes() {
        return quoteRepository.findAll().stream()
                .map(quote -> modelMapper.map(quote, RequestQuoteDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<RequestQuoteDTO> getQuoteById(Long id) {
        return quoteRepository.findById(id)
                .map(quote -> modelMapper.map(quote, RequestQuoteDTO.class));
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public List<RequestQuoteDTO> getQuotesByOwnerId(Long id) {
        return quoteRepository.findAll().stream()
                .filter(quote -> quote.getTractOwner() != null && id.equals(quote.getTractOwner().getId()))
                .map(quote -> modelMapper.map(quote, RequestQuoteDTO.class))
                .collect(Collectors.toList());
    }
}

