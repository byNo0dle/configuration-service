package com.ufostyle.configurationservice.mappers;

import com.ufostyle.configurationservice.entities.Configuration;
import com.ufostyle.configurationservice.noodle.SetUpCs;
import java.math.BigDecimal;

/**
 * Esto es la clase ConfigurationMapper.
 */
public class ConfigurationMapper {

  /**
   * Esto es la clase estatica de Configuration.
   *
   * @param setUpCs esto es el parametro setUpCs
   * @return esto retorna Configuration
   */
  public static Configuration configuration(SetUpCs setUpCs) {
    return Configuration.builder()
        .idConfiguration(setUpCs.getIdConfiguration())
        .costMaintenance(setUpCs.getCostMaintenance().doubleValue())
        .quantityMovement(setUpCs.getQuantityMovement())
        .quantityCredit(setUpCs.getQuantityCredit())
        .specifyDate(setUpCs.getSpecifyDate())
        .build();
  }

  /**
   * Esto es la clase estatica de SetUpCs.
   *
   * @param configuration esto es el paremtro configuration
   * @return esto retorna el setUpCs
   */
  public static SetUpCs setUpCs(Configuration configuration) {
    SetUpCs setUpCs = new SetUpCs();
    setUpCs.setIdConfiguration(configuration.getIdConfiguration());
    setUpCs.setCostMaintenance(BigDecimal.valueOf(
        configuration.getCostMaintenance().doubleValue()));
    setUpCs.setQuantityMovement(configuration.getQuantityMovement());
    setUpCs.setQuantityCredit(configuration.getQuantityCredit());
    setUpCs.setSpecifyDate(configuration.getSpecifyDate());
    return setUpCs;
  }
}
