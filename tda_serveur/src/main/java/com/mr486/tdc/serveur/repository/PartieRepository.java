package com.mr486.tdc.serveur.repository;

import com.mr486.tdc.serveur.model.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieRepository extends JpaRepository<Partie, Long>{
}
