package com.catolica.terraz.service;

import com.catolica.terraz.dto.QuoteDTO;
import com.catolica.terraz.model.Factor;
import com.catolica.terraz.model.Quote;
import com.catolica.terraz.repository.FactorRepository;
import com.catolica.terraz.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final FactorService factorService;
    private final FactorRepository factorRepository;

    public QuoteDTO createQuote(QuoteDTO quoteDTO) {
        Quote quote = toEntity(quoteDTO);
        Quote savedQuote = quoteRepository.save(quote);
        return toDTO(savedQuote);
    }

    public List<QuoteDTO> getAllQuotes() {
        return quoteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuoteDTO> getQuoteById(Long id) {
        return quoteRepository.findById(id)
                .map(this::toDTO);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public QuoteDTO toDTO(Quote quote) {
        return QuoteDTO.builder()
                .id(quote.getId())
                .tract(quote.getTract())
                .tractOwner(quote.getTractOwner())
                .factorIds(quote.getFactorList().stream()
                        .map(Factor::getId)
                        .collect(Collectors.toList()))
                .totalPrice(quote.getTotalPrice())
                .createDate(quote.getCreateDate())
                .build();
    }

    public Quote toEntity(QuoteDTO quoteDTO) {
        List<Factor> factors = quoteDTO.getFactorIds().stream()
                .map(id -> factorRepository.getById(id))
                .collect(Collectors.toList());

        return Quote.builder()
                .id(quoteDTO.getId())
                .tract(quoteDTO.getTract())
                .tractOwner(quoteDTO.getTractOwner())
                .factorList(factors)
                .totalPrice(quoteDTO.getTotalPrice())
                .createDate(quoteDTO.getCreateDate())
                .build();
    }

}
