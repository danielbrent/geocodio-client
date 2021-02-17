package io.github.danielbrent.geocodio.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.danielbrent.geocodio.exception.GeocodingException;
import io.github.danielbrent.geocodio.model.GeocodioApiResponse;
import io.github.danielbrent.geocodio.model.batch.GeocodioBatchApiResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class GeocodioClient {

  private String version = "v1.6";
  private String host = "api.geocod.io";
  private String apiKey;
  private ObjectMapper objectMapper = new ObjectMapper();

  private int defaultTimeoutMillis = 60 * 1000;

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
   * @throws URISyntaxException
   * @throws IOException
   * @throws GeocodingException
   */
  public GeocodioBatchApiResponse batchGeocode(List<String> addresses)
      throws URISyntaxException, IOException, GeocodingException {
    return batchGeocode(addresses, defaultTimeoutMillis);
  }

  /**
   * Perform a batch geocoding request to the Geocodio API with the specific timeout value set for
   * the connection. Geocodio's documentation notes that a batch of 10,000 addresses will take about
   * 600 seconds to process
   * 
   * @param addresses The addresses to geocode
   * @param timeout The timeout for the connection, in milliseconds
   * @return
   * @throws URISyntaxException
   * @throws IOException
   * @throws GeocodingException
   */
  public GeocodioBatchApiResponse batchGeocode(List<String> addresses, int timeout)
      throws URISyntaxException, IOException, GeocodingException {

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

    String responseString = executeRequest(requestJson, post, timeout);
    GeocodioBatchApiResponse queryResult =
        objectMapper.readValue(responseString, GeocodioBatchApiResponse.class);

    if (queryResult.getResults() == null) {
      throw new GeocodingException(responseString);
    }

    return queryResult;

  }

  /**
   * Peform a single geocoding request to the Geocodio API
   * 
   * @param address The address to geocode
   * @param timeout The timeout for the connection, in milliseconds
   * @return
   * @throws URISyntaxException
   * @throws IOException
   * @throws GeocodingException
   */

  public GeocodioApiResponse singleGeocode(String address)
      throws URISyntaxException, IOException, GeocodingException {
    return singleGeocode(address, defaultTimeoutMillis);
  }


  /**
   * Peform a single geocoding request to the Geocodio API with the specific timeout value set for
   * the connection
   * 
   * @param address The address to geocode
   * @param timeout The timeout for the connection, in milliseconds
   * @return
   * @throws URISyntaxException
   * @throws IOException
   * @throws GeocodingException
   */
  public GeocodioApiResponse singleGeocode(String address, int timeout)
      throws URISyntaxException, IOException, GeocodingException {

    String requestJson = objectMapper.writeValueAsString(address);

    URI singleGeocodeUri = new URIBuilder()
        .setScheme("https")
        .setHost(host)
        .setPath(version + "/geocode")
        .addParameter("api_key", this.apiKey)
        .addParameter("q", address)
        .build();


    HttpGet get = new HttpGet(singleGeocodeUri);
    String responseString = executeRequest(requestJson, get, timeout);

    GeocodioApiResponse queryResult =
        objectMapper.readValue(responseString, GeocodioApiResponse.class);

    if (queryResult.getInput() == null) {
      throw new GeocodingException(responseString);
    }

    return queryResult;

  }

  private String executeRequest(String requestJson, HttpUriRequest request, int timeout)
      throws IOException {

    try (CloseableHttpClient client = buildClient(timeout)) {

      CloseableHttpResponse response = client.execute(request);
      return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

  }

  private CloseableHttpClient buildClient(int timeout) {

      RequestConfig config = RequestConfig.custom()
        .setConnectionRequestTimeout(timeout)
        .setSocketTimeout(timeout)
        .setConnectTimeout(timeout)
          .build();

      return HttpClientBuilder.create().setDefaultRequestConfig(config).build();



  }


}
