package com.ufostyle.configurationservice.services;

import com.ufostyle.configurationservice.entities.Configuration;
import com.ufostyle.configurationservice.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

  @Autowired
  ConfigurationRepository configurationRepository;

  @Autowired
  RestTemplate restTemplate;


  @Override
  public Flux<Configuration> findAll() {
    return configurationRepository.findAll();
  }

  @Override
  public Mono<Configuration> findById(String idConfiguration) {
    return configurationRepository.findById(idConfiguration);
  }

  @Override
  public Mono<Configuration> save(Configuration configuration) {
    return configurationRepository.insert(configuration);
  }

  @Override
  public Mono<Configuration> update(Configuration configuration) {
    return configurationRepository.save(configuration);
  }

  @Override
  public Mono<Void> delete(String id) {
    return configurationRepository.deleteById(id);
  }
}
