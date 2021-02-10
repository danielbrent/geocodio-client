package io.github.danielbrent.geocodio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Object representing the {@code "address_components"} JSON object from a geocoding query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressComponents {

  @JsonProperty
  private String number;
  @JsonProperty
  private String predirectional;
  @JsonProperty
  private String street;
  @JsonProperty
  private String suffix;
  @JsonProperty("formatted_street")
  private String formattedStreet;
  @JsonProperty
  private String city;
  @JsonProperty
  private String county;
  @JsonProperty
  private String state;
  @JsonProperty
  private String zip;
  @JsonProperty
  private String country;


  public String getNumber() {
    return number;
  }

  public String getPredirectional() {
    return predirectional;
  }

  public String getStreet() {
    return street;
  }

  public String getSuffix() {
    return suffix;
  }

  public String getFormattedStreet() {
    return formattedStreet;
  }

  public String getCity() {
    return city;
  }

  public String getCounty() {
    return county;
  }

  public String getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public String getCountry() {
    return country;
  }


}
