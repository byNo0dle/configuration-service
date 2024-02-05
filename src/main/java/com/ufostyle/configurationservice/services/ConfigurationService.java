package com.ufostyle.configurationservice.services;

import com.ufostyle.configurationservice.entities.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConfigurationService {

  Flux<Configuration> findAll();

  Mono<Configuration> findById(String idConfiguration);

  Mono<Configuration> save(Configuration configuration);

  Mono<Configuration> update(Configuration configuration);

  Mono<Void> delete(String id);
}
