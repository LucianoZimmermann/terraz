package com.catolica.terraz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestQuoteDTO {
    private Long id;
    private TractDTO tract;
    private TractOwnerDTO tractOwner;
    private List<FactorDTO> factors;
}
