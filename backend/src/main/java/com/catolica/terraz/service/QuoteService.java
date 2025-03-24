package com.catolica.terraz.service;

import com.catolica.terraz.dto.QuoteDTO;
import com.catolica.terraz.model.Factor;
import com.catolica.terraz.model.Quote;
import com.catolica.terraz.repository.FactorRepository;
import com.catolica.terraz.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final FactorRepository factorRepository;
    private final ModelMapper modelMapper;

    public QuoteDTO createQuote(QuoteDTO quoteDTO) {
        Quote quote = modelMapper.map(quoteDTO, Quote.class);
        if (quoteDTO.getFactorIds() != null) {
            List<Factor> factors = quoteDTO.getFactorIds().stream()
                    .map(factorRepository::getById)
                    .collect(Collectors.toList());
            quote.setFactorList(factors);
        }
        Quote savedQuote = quoteRepository.save(quote);
        QuoteDTO savedQuoteDTO = modelMapper.map(savedQuote, QuoteDTO.class);
        if (savedQuote.getFactorList() != null) {
            savedQuoteDTO.setFactorIds(savedQuote.getFactorList().stream()
                    .map(Factor::getId)
                    .collect(Collectors.toList()));
        }
        return savedQuoteDTO;
    }

    public List<QuoteDTO> getAllQuotes() {
        return quoteRepository.findAll().stream().map(quote -> {
            QuoteDTO dto = modelMapper.map(quote, QuoteDTO.class);
            if (quote.getFactorList() != null) {
                dto.setFactorIds(quote.getFactorList().stream()
                        .map(Factor::getId)
                        .collect(Collectors.toList()));
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<QuoteDTO> getQuoteById(Long id) {
        return quoteRepository.findById(id).map(quote -> {
            QuoteDTO dto = modelMapper.map(quote, QuoteDTO.class);
            if (quote.getFactorList() != null) {
                dto.setFactorIds(quote.getFactorList().stream()
                        .map(Factor::getId)
                        .collect(Collectors.toList()));
            }
            return dto;
        });
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public List<QuoteDTO> getQuotesByOwnerId(Long id) {
        return quoteRepository.findAll().stream()
                .filter(quote -> quote.getTractOwner() != null && id.equals(quote.getTractOwner().getId()))
                .map(quote -> {
                    QuoteDTO dto = modelMapper.map(quote, QuoteDTO.class);
                    if (quote.getFactorList() != null) {
                        dto.setFactorIds(quote.getFactorList().stream()
                                .map(Factor::getId)
                                .collect(Collectors.toList()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
