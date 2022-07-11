package com.mr486.tdc.serveur.service;

import com.mr486.tdc.serveur.model.Copain;
import com.mr486.tdc.serveur.repository.CopainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CopainService {

  @Autowired
  private CopainRepository copainRepository;

  public Boolean existe(Long id) {
    return copainRepository.existsById(id);
  }

  public Copain copainAvecId(Long id) {
    return copainRepository.findById(id).orElse(null);
  }

  public List<Copain> toutLesCopains() {
    List<Copain> copains;
    copains = copainRepository.findAll();
    return copains;
  }

  public Copain enregistreCopain(Copain copain) {
    return copainRepository.save(copain);
  }

  public Copain updateCopain(Long id, Copain copain) {
    copain.setId(id);
    return copainRepository.save(copain);
  }

  public void supprimeCopainAvecId(Long id) {
    this.copainRepository.deleteById(id);
  }

  public Long compte() {
    return copainRepository.count();
  }
}
