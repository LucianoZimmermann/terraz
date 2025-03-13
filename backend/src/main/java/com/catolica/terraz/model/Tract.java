package com.catolica.terraz.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private Float squareMeters;
}
