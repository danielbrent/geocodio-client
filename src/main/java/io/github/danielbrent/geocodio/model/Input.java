package io.github.danielbrent.geocodio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Object representing the {@code "input"} JSON object from a geocoding query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class Input {

  @JsonProperty("address_components")
  private AddressComponents addressComponents;

  @JsonProperty("formatted_address")
  private String formattedAddress;

  public AddressComponents getAddressComponents() {
    return addressComponents;
  }

  public String getFormattedAddress() {
    return formattedAddress;
  }



}
