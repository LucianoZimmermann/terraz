package com.catolica.terraz.controller;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.service.FactorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/factors")
@AllArgsConstructor
public class FactorController {
    private final FactorService factorService;

    @GetMapping
    public List<FactorDTO> getAllFactors(){
        return factorService.getAllFactors();
    }
}
