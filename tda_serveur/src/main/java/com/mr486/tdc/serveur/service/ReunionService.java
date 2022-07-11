package com.mr486.tdc.serveur.service;

import com.mr486.tdc.serveur.model.Reunion;
import com.mr486.tdc.serveur.repository.ReunionRepository;
import com.mr486.tdc.serveur.tools.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReunionService{

  @Autowired
  private ReunionRepository reunionRepository;

  public Boolean existe(Long id) {
    return this.reunionRepository.existsById(id);
  }

  public Reunion reunionAvecId(long id) throws ResourceNotFoundException {
    Reunion reunion = this.reunionRepository.findById(id).orElse(null);
    if (reunion==null) {
      throw new ResourceNotFoundException("Reunion introuvable avec l'id: " + id);
    }
    else return reunion;
  }

  public List<Reunion> toutesLesReunions() {
    return new ArrayList<>(this.reunionRepository.findAll());
  }

  public Reunion enregistreReunion(Reunion reunion) {
    return reunionRepository.save(reunion);
  }

  public void supprimeReunionAvecId(long id) {
    this.reunionRepository.deleteById(id);
  }

  public long compte() {
    return this.reunionRepository.count();
  }
}
