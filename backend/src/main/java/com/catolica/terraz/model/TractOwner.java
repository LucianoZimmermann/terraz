package com.catolica.terraz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tract_owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TractOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "tract_id")
    private Tract tract;
}
