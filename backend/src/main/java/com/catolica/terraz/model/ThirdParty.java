package com.catolica.terraz.model;

import com.catolica.terraz.enums.FactorTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "third_parties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String cnpj;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "third_party_factor_type")
    private FactorTypeEnum thirdPartyFactorTypeEnum;
}
