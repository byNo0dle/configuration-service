package com.ufostyle.configurationservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Esto es la clase Configuration.
 */
@Builder
@Data
@Document(collection = "configurations")
public class Configuration {

  @Id
  String idConfiguration;
  Double costMaintenance;
  Integer quantityMovement;
  Integer quantityCredit;
  String specifyDate;

  @Override
  public String toString() {
    return "Configuration ["
        +
        "idConfiguration='"
        + idConfiguration
        +
        '\''
        +
        ", costMaintenance="
        + costMaintenance
        +
        ", quantityMovement="
        + quantityMovement
        +
        ", quantityCredit="
        + quantityCredit
        +
        ", specifyDate='"
        + specifyDate
        + '\''
        +
        "]";
  }
}
