package io.github.danielbrent.geocodio.model.batch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.danielbrent.geocodio.model.GeocodioApiResponse;
import java.util.List;

/**
 * Model Object representing the raw response string from a batch geocoding call. A batch response
 * consists of a list of {@linkplain BatchResult} objects, which pair the query string to the
 * {@linkplain GeocodioApiResponse} for that individual query
 */
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodioBatchApiResponse {

  @JsonProperty
  List<BatchResult> results;

  public List<BatchResult> getResults() {
    return results;
  }
}
