package io.github.danielbrent.geocodio.model.batch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.danielbrent.geocodio.model.GeocodioApiResponse;

/**
 * Model Object representing an individual result from a batch query. BatchResult objects consist of
 * the original query value and a {@linkplain GeocodioApiResponse} response object, which is equivalent
 * to a response for a single geocoding query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchResult {

  @JsonProperty
  private String query;
  @JsonProperty
  private GeocodioApiResponse response;

  public String getQuery() {
    return query;
  }

  public GeocodioApiResponse getResponse() {
    return response;
  }



}
