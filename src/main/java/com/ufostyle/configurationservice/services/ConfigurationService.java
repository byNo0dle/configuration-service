package com.ufostyle.configurationservice.services;

import com.ufostyle.configurationservice.entities.Configuration;
import com.ufostyle.configurationservice.mappers.ConfigurationMapper;
import com.ufostyle.configurationservice.noodle.SetUpCs;
import com.ufostyle.configurationservice.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Esto es la clase ConfigurationService.
 */
@Service
public class ConfigurationService {

  @Autowired
  ConfigurationRepository configurationRepository;

  @Autowired
  RestTemplate restTemplate;

  public Flux<SetUpCs> findAll() {
    return configurationRepository.findAll()
        .flatMap(configuration1 -> Mono.just(ConfigurationMapper.setUpCs(configuration1)));
  }

  public Mono<SetUpCs> save(SetUpCs setUpCs) {
    return configurationRepository.save(ConfigurationMapper.configuration(setUpCs))
        .flatMap(configuration2 -> Mono.just(ConfigurationMapper.setUpCs(configuration2)));
  }

  public Mono<SetUpCs> update(SetUpCs setUpCs) {
    return configurationRepository.save(ConfigurationMapper.configuration(setUpCs))
        .flatMap(configuration3 -> Mono.just(ConfigurationMapper.setUpCs(configuration3)));
  }

  public Mono<SetUpCs> findById(String idConfiguration) {
    return configurationRepository.findById(idConfiguration)
        .flatMap(configuration4 -> Mono.just(ConfigurationMapper.setUpCs(configuration4)));
  }

  public Mono<Void> deleteById(String idConfiguration) {
    return configurationRepository.deleteById(idConfiguration);
  }
}
