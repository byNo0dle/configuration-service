package com.ufostyle.configurationservice.repositories;

import com.ufostyle.configurationservice.entities.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends ReactiveMongoRepository<Configuration, String> {
}
