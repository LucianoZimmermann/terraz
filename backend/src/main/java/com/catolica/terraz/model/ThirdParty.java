package com.catolica.terraz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "thirdPartys")
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
    @Column
    private String name;

    @NotNull
    @Column
    private String cnpj;
}
