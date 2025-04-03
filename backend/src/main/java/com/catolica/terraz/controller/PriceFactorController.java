package com.catolica.terraz.controller;

import com.catolica.terraz.dto.PriceFactorDTO;
import com.catolica.terraz.service.PriceFactorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price-factors")
@RequiredArgsConstructor
public class PriceFactorController {

  private final PriceFactorService priceFactorService;

  @PostMapping
  public ResponseEntity<PriceFactorDTO> savePriceFactor(
      @RequestBody PriceFactorDTO priceFactorDTO) {
    PriceFactorDTO saved = priceFactorService.savePriceFactor(priceFactorDTO);
    return ResponseEntity.ok(saved);
  }

  @GetMapping
  public ResponseEntity<List<PriceFactorDTO>> getAllPriceFactors() {
    List<PriceFactorDTO> list = priceFactorService.getAllPriceFactors();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PriceFactorDTO> getById(@PathVariable Long id) {
    return priceFactorService
        .getPriceFactorById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
