package io.github.danielbrent.geocodio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Object representing a single entry from the {@code "results"} JSON object array from a
 * geocoding query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

  @JsonProperty("address_components")
  private AddressComponents addressComponents;
  @JsonProperty("formatted_address")
  private String formattedAddress;
  @JsonProperty
  private Coordinates location;
  @JsonProperty
  private float accuracy;
  @JsonProperty(value = "accuracy_type")
  private String accuracyType;
  @JsonProperty
  private String source;

  public AddressComponents getAddressComponents() {
    return addressComponents;
  }
  public String getFormattedAddress() {
    return formattedAddress;
  }

  public Coordinates getLocation() {
    return location;
  }
  public float getAccuracy() {
    return accuracy;
  }
  public String getAccuracyType() {
    return accuracyType;
  }
  public String getSource() {
    return source;
  }




}
