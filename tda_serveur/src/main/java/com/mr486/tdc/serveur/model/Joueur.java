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
public class Joueur implements Serializable {

  private static final long serialVersionUID = 4048798961366546485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne @JoinColumn(name="reunion", nullable=false)
  private Reunion reunion;

  @ManyToOne @JoinColumn(name="copain", nullable=false)
  private Copain copain;

  @Column(name = "est_guest")
  private Boolean estGuest;

  @Column(name = "dette", nullable = false)
  private Integer dette;

  @Column(name = "dette_active")
  private Boolean detteActive;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Joueur joueur = (Joueur) o;
    return id != null && Objects.equals(id, joueur.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
