package com.mr486.tdc.serveur.tango.component;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tg_component")
public class TgComponent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "type_component", nullable = false)
  private TypeComponent typeComponent;

  @Column(name = "id_parent")
  private Long idParent;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "file_extension")
  private TypeComponent fileExtension;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    TgComponent that = (TgComponent) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
