package com.catolica.terraz.dto.request;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.dto.TractDTO;
import com.catolica.terraz.dto.TractOwnerDTO;
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
