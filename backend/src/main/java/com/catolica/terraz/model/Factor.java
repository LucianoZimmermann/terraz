package com.catolica.terraz.model;

import com.catolica.terraz.enums.FactorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "factors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "third_party_id")
    private ThirdParty thirdParty;

    @NotNull
    private Float materialCost;

    @NotNull
    private Float laborCost;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "factor_type")
    private FactorType factorType;
}
