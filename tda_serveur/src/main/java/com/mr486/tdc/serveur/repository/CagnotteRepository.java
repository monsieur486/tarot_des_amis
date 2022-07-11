package com.mr486.tdc.serveur.repository;

import com.mr486.tdc.serveur.model.Cagnotte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CagnotteRepository extends JpaRepository<Cagnotte, Long>{
}
