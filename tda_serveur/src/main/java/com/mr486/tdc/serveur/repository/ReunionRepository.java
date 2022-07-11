package com.mr486.tdc.serveur.repository;

import com.mr486.tdc.serveur.model.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long>{
  List<Reunion> findAllByCagnotteId(Long id);
}
