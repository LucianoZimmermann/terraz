package com.catolica.terraz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

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

    @Column
    @NotNull
    private Float totalPrice;

    @NotNull
    private LocalDateTime createDate;

}
