package com.mr486.tdc.serveur.controller.api;

import com.mr486.tdc.serveur.model.Copain;
import com.mr486.tdc.serveur.model.Message;
import com.mr486.tdc.serveur.service.CopainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiCopainsController {

  private final CopainService copainService;

  public ApiCopainsController(CopainService copainService) {
    this.copainService = copainService;
  }

  @GetMapping(value = "/copains")
  public ResponseEntity<Object> toutLesCopains() {
    try {
      List<Copain> result = copainService.toutLesCopains();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/copains/{id}")
  public ResponseEntity<Object> getCopain(@PathVariable Long id) {
    try {
      Optional<Copain> result = Optional.ofNullable(copainService.copainAvecId(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Copain introuvable avec l'ID: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/copains")
  public ResponseEntity<Object> save(@RequestBody Copain copain) {
    try {
      Copain result = copainService.enregistreCopain(copain);
      return Message.generateResponse("Copain créé avec succès.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/copains/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Copain copain) {
    try {
      Copain result = copainService.updateCopain(id, copain);
      return Message.generateResponse("Copain mis à jour avec succès.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/copains/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(copainService.existe(id))){
      try {
        copainService.supprimeCopainAvecId(id);
        return Message.generateResponse("Copain supprimé avec succès.", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Copain introuvable avec l'ID: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
