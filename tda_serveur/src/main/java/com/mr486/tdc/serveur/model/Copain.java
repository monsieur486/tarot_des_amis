package com.mr486.tdc.serveur.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Copain implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nom", length = 150, nullable = false)
  @NotBlank(message = "Pas de nom vide autorisé")
  private String nom;

  @Column(name = "image", length = 150)
  private String image;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Copain copain = (Copain) o;
    return id != null && Objects.equals(id, copain.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
