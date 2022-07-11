package com.mr486.tdc.serveur.repository;

import com.mr486.tdc.serveur.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long>{
}
