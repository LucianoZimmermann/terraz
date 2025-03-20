package com.catolica.terraz.dto;

import com.catolica.terraz.model.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TractDTO {
    private Long id;
    private Float squareMeters;
    private Address address;
}
