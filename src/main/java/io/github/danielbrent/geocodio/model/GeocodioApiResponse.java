package io.github.danielbrent.geocodio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Model Object for the raw JSON response for a geocoding API request
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodioApiResponse {

  @JsonProperty
  private Input input;
  @JsonProperty
  private List<Result> results;

  public Input getInput() {
    return input;
  }

  public List<Result> getResults() {
    return results;
  }


}
