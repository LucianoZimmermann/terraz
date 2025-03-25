package com.catolica.terraz.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseQuoteDTO {
    private Long id;
    private TractDTO tract;
    private List<FactorDTO> factors;
    private Double totalPrice;
    private LocalDateTime createDate;
}
