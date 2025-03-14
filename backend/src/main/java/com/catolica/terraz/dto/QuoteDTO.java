package com.catolica.terraz.dto;

import com.catolica.terraz.model.Factor;
import com.catolica.terraz.model.Quote;
import com.catolica.terraz.model.Tract;
import com.catolica.terraz.model.TractOwner;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static QuoteDTO fromEntity(Quote quote) {
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
}
