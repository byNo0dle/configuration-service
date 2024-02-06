package com.ufostyle.configurationservice.controllers;

import com.ufostyle.configurationservice.noodle.SetUpCs;
import com.ufostyle.configurationservice.services.ConfigurationService;
import com.ufostyle.configurationservice.ufo.ConfigurationApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esto es la clase ApiDelegateConfiguration que sustituye al controller basico.
 */
@Component
public class ApiDelegateConfiguration implements ConfigurationApiDelegate {

  @Autowired
  ConfigurationService configurationService;

  @Override
  public Mono<ResponseEntity<Flux<SetUpCs>>> findAllConfiguration(ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(configurationService.findAll()));
  }

  @Override
  public Mono<ResponseEntity<SetUpCs>> saveConfiguration(
      Mono<SetUpCs> setUpCs, ServerWebExchange exchange) {
    return setUpCs
        .flatMap(requestSetUpCs1 -> configurationService.save(requestSetUpCs1))
        .flatMap(createSetUpCs -> Mono.just(ResponseEntity.ok(createSetUpCs)));
  }

  @Override
  public Mono<ResponseEntity<SetUpCs>> updateConfiguration(
      Mono<SetUpCs> setUpCs, ServerWebExchange exchange) {
    return setUpCs
        .flatMap(requestSetUpCs2 -> configurationService.update(requestSetUpCs2))
        .flatMap(createSetUpCs -> Mono.just(ResponseEntity.ok(createSetUpCs)));
  }

  @Override
  public Mono<ResponseEntity<SetUpCs>> findByIdConfiguration(
      String idConfiguration, ServerWebExchange exchange) {
    return configurationService.findById(idConfiguration)
        .flatMap(findAllSetUpCs -> Mono.just(ResponseEntity.ok(findAllSetUpCs)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteConfiguration(
      String idConfiguration, ServerWebExchange exchange) {
    return configurationService.deleteById(idConfiguration)
        .then(Mono.just(ResponseEntity.noContent().<Void>build()))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }
}
