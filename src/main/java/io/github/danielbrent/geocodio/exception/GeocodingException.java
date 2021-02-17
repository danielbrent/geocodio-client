package io.github.danielbrent.geocodio.exception;

public class GeocodingException extends Exception {

  private static final long serialVersionUID = 1L;

  public GeocodingException(String message) {
    super(message);
  }

  public GeocodingException(Throwable t) {
    super(t);
  }

  public GeocodingException(String message, Throwable t) {
    super(message, t);
  }

}
