package com.mr486.tdc.serveur.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Contrat implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nom", length = 150, nullable = false)
  private String nom;

  @Column(name = "initiale", length = 6, nullable = false)
  private String initiale;

  @Column(name = "points")
  private Integer points;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Contrat contrat = (Contrat) o;
    return id != null && Objects.equals(id, contrat.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
