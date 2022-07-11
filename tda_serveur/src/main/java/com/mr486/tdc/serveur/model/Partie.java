package com.mr486.tdc.serveur.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Partie implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne @JoinColumn(name="reunion", nullable=false)
  private Reunion reunion;

  @ManyToOne @JoinColumn(name="contrat", nullable=false)
  private Contrat contrat;

  @ManyToOne @JoinColumn(name="preneur")
  private Copain preneur;

  @ManyToOne @JoinColumn(name="appel")
  private Copain appel;

  @Column(name = "contrat_reussi", nullable = false)
  private Boolean contratReussi;

  @Column(name = "points", nullable = false)
  private Integer points;

  @ManyToOne @JoinColumn(name = "petit_au_bout")
  private Copain petitAuBout;

  @Column(name = "chelem_reussi", nullable = false)
  private Boolean chelemReussi;

  @Column(name = "date_creation")
  private Timestamp dateCreation;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Partie partie = (Partie) o;
    return id != null && Objects.equals(id, partie.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
