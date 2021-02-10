package io.github.danielbrent.geocodio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Object representing the {@code "coordinates"} JSON object from a geocoding query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

  @JsonProperty("lat")
  private double latitude;
  @JsonProperty("lng")
  private double longitude;

  public double getLatitiude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }


}
