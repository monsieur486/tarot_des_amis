package com.mr486.tdc.serveur.service;

import com.mr486.tdc.serveur.model.Cagnotte;
import com.mr486.tdc.serveur.repository.CagnotteRepository;
import com.mr486.tdc.serveur.tools.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CagnotteService{

  @Autowired
  private CagnotteRepository cagnotteRepository;

  public Boolean existe(Long id) {
    return this.cagnotteRepository.existsById(id);
  }

  public Cagnotte cagnotteAvecId(long id) throws ResourceNotFoundException {
    Cagnotte cagnotte = this.cagnotteRepository.findById(id).orElse(null);
    if (cagnotte==null) {
      throw new ResourceNotFoundException("Cagnotte introuvable avec l'id: " + id);
    }
    else return cagnotte;
  }

  public List<Cagnotte> toutesLesCagnottes() {
    return new ArrayList<>(this.cagnotteRepository.findAll());
  }

  public Cagnotte enregistreCagnotte(Cagnotte cagnotte) {
    return cagnotteRepository.save(cagnotte);
  }

  public void supprimeCagnotteAvecId(long id) {
    this.cagnotteRepository.deleteById(id);
  }

  public long compte() {
    return this.cagnotteRepository.count();
  }
}
