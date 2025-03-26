package com.catolica.terraz.dto.request;

import com.catolica.terraz.dto.FactorDTO;
import com.catolica.terraz.dto.TractDTO;
import com.catolica.terraz.dto.TractOwnerDTO;
import java.util.List;
import lombok.*;

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
