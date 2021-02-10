# geocodio-client
A Java client for using the Geocodio geocoding API


To use the client to perform a single address geocode

```java
      GeocodioClient client = new GeocodioClient("myApiKey");
      GeocodioApiResponse response = client.singleGeocode("1600 pennsylvania ave washington dc");
```

To use the client to perform a batch address geocode

```java
      GeocodioClient client = new GeocodioClient("myApiKey");
      GeocodioBatchApiResponse batchResponse = client.batchGeocode(Arrays.asList(
          "1600 pennsylvania ave washington dc",
          "20 W 34th St, New York, NY 10001"));
```
