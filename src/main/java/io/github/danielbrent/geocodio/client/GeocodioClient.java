package io.github.danielbrent.geocodio.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.danielbrent.geocodio.model.GeocodioApiResponse;
import io.github.danielbrent.geocodio.model.batch.GeocodioBatchApiResponse;
import java.net.URI;
import java.util.List;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GeocodioClient {

  private String version = "v1.6";
  private String host = "api.geocod.io";
  private String apiKey;
  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Create a GeocodioClient with the specified {@code apiKey}
   * 
   * @param apiKey
   */
  public GeocodioClient(String apiKey) {
    this.apiKey = apiKey;
  }


  /**
   * Perform a batch geocoding request to the Geocodio API
   * 
   * @param addresses The addresses to geocode
   * @return
   * @throws Exception
   */
  public GeocodioBatchApiResponse batchGeocode(List<String> addresses) throws Exception {

    String requestJson = objectMapper.writeValueAsString(addresses);

    URI batchGeocodeUri = new URIBuilder()
        .setScheme("https")
        .setHost(host)
        .setPath(version + "/geocode")
        .addParameter("api_key", this.apiKey)
        .build();

    HttpPost post = new HttpPost(batchGeocodeUri);
    HttpEntity body = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
    post.setEntity(body);

    String responseString = executeRequest(requestJson, post);
    GeocodioBatchApiResponse queryResult =
        objectMapper.readValue(responseString, GeocodioBatchApiResponse.class);
    return queryResult;

  }

  /**
   * Peform a single geocoding request to the Geocodio API
   * 
   * @param address The address to geocode
   * @return
   * @throws Exception
   */
  public GeocodioApiResponse singleGeocode(String address) throws Exception {

    String requestJson = objectMapper.writeValueAsString(address);

    URI singleGeocodeUri = new URIBuilder()
        .setScheme("https")
        .setHost(host)
        .setPath(version + "/geocode")
        .addParameter("api_key", this.apiKey)
        .addParameter("q", address)
        .build();


    HttpGet get = new HttpGet(singleGeocodeUri);
    String responseString = executeRequest(requestJson, get);

    GeocodioApiResponse queryResult =
        objectMapper.readValue(responseString, GeocodioApiResponse.class);

    return queryResult;

  }

  private String executeRequest(String requestJson, HttpUriRequest request) throws Exception {

    try (CloseableHttpClient client = buildClient()) {
      CloseableHttpResponse response = client.execute(request);
      return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

  }

  // TODO: Add support for extending the timeout on the request for batch requests.
  private CloseableHttpClient buildClient() {
    return HttpClients.createDefault();
  }


}
