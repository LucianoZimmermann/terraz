package com.catolica.terraz.dto;

import com.catolica.terraz.model.Tract;
import com.catolica.terraz.model.TractOwner;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteDTO {
    private Long id;
    private Tract tract;
    private TractOwner tractOwner;
    private List<Long> factorIds;
    private Float totalPrice;
    private LocalDateTime createDate;
}
