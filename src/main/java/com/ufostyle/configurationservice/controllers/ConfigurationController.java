package com.ufostyle.configurationservice.controllers;

import com.ufostyle.configurationservice.entities.Configuration;
import com.ufostyle.configurationservice.services.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

  Logger log = LoggerFactory.getLogger(ConfigurationController.class);

  @Autowired
  ConfigurationService configurationService;

  @GetMapping
  public Flux<Configuration> findAll() {
    return configurationService.findAll();
  }

  @PostMapping
  public Mono<ResponseEntity<Configuration>> save(@RequestBody Configuration configuration) {
    return configurationService.save(configuration).map(_configuration -> ResponseEntity.ok().body(_configuration))
        .onErrorResume(e -> {
          log.info("Error:" + e.getMessage());
          return Mono.just(ResponseEntity.badRequest().build());
        });
  }

  @GetMapping("/{idConfiguration}")
  public Mono<ResponseEntity<Configuration>> findById(@PathVariable(name = "idConfiguration") String idConfiguration) {
    return configurationService.findById(idConfiguration)
        .map(configuration -> ResponseEntity.ok().body(configuration)).onErrorResume(e -> {
          log.info(e.getMessage());
          return Mono.just(ResponseEntity.badRequest().build());
        }).defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @PutMapping
  public Mono<ResponseEntity<Configuration>> update(@RequestBody Configuration configuration) {

    Mono<Configuration> mono = configurationService.findById(configuration.getIdConfiguration())
        .flatMap(objConfiguration -> {
          log.info("Update:[new]" + configuration + " [Old]:" + objConfiguration);
          return configurationService.update(configuration);
        });

    return mono.map(_configuration -> {
      log.info("Status:" + HttpStatus.OK);
      return ResponseEntity.ok().body(_configuration);
    }).onErrorResume(e -> {
      log.info("Status:" + HttpStatus.BAD_REQUEST + " menssage" + e.getMessage());
      return Mono.just(ResponseEntity.badRequest().build());
    }).defaultIfEmpty(ResponseEntity.noContent().build());

  }

  @DeleteMapping("/{idConfiguration}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable(name = "idConfiguration") String idConfiguration) {
    return configurationService.findById(idConfiguration).flatMap(configuration -> {
      return configurationService.delete(configuration.getIdConfiguration())
          .then(Mono.just(ResponseEntity.ok().build()));
    });
  }
}
