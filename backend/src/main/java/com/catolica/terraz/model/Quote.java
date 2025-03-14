package com.catolica.terraz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tract_id")
    private Tract tract;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tract_owner_id")
    private TractOwner tractOwner;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "quote_factors",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "factor_id")
    )
    private List<Factor> factorList;

    @Column
    @NotNull
    private Float totalPrice;

    @NotNull
    private LocalDateTime createDate;
}
