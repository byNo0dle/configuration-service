package com.ufostyle.configurationservice.mappers;

import com.ufostyle.configurationservice.entities.Configuration;
import com.ufostyle.configurationservice.noodle.SetUpCs;

import java.math.BigDecimal;

public class ConfigurationMapper {

  public static Configuration configuration(SetUpCs setUpCs) {
    return Configuration.builder()
        .idConfiguration(setUpCs.getIdConfiguration())
        .costMaintenance(setUpCs.getCostMaintenance().doubleValue())
        .quantityMovement(setUpCs.getQuantityMovement())
        .quantityCredit(setUpCs.getQuantityCredit())
        .specifyDate(setUpCs.getSpecifyDate())
        .build();
  }

  public static SetUpCs setUpCs(Configuration configuration) {
    SetUpCs setUpCs = new SetUpCs();
    setUpCs.setIdConfiguration(configuration.getIdConfiguration());
    setUpCs.setCostMaintenance(BigDecimal.valueOf(configuration.getCostMaintenance().doubleValue()));
    setUpCs.setQuantityMovement(configuration.getQuantityMovement());
    setUpCs.setQuantityCredit(configuration.getQuantityCredit());
    setUpCs.setSpecifyDate(configuration.getSpecifyDate());
    return setUpCs;
  }
}
