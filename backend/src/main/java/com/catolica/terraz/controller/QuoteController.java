package com.catolica.terraz.controller;

import com.catolica.terraz.dto.QuoteDTO;
import com.catolica.terraz.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO quoteDTO) {
        QuoteDTO createdQuote = quoteService.createQuote(quoteDTO);
        return ResponseEntity.ok(createdQuote);
    }

    @GetMapping
    public ResponseEntity<List<QuoteDTO>> getAllQuotes() {
        List<QuoteDTO> quotes = quoteService.getAllQuotes();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable Long id) {
        Optional<QuoteDTO> quoteDTO = quoteService.getQuoteById(id);
        return quoteDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
