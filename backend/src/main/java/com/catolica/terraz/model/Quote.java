package com.catolica.terraz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

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

  @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
  @NotNull
  @Size(min = 5)
  private List<Factor> factorList;

  @Column @NotNull private Double totalPrice;

  @NotNull private LocalDateTime createDate;
}
