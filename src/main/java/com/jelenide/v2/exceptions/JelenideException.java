package com.jelenide.v2.exceptions;

/**
 * Created by apop on 7/20/2017.
 */
public class JelenideException extends RuntimeException {
  public JelenideException() {
    super();
  }

  public JelenideException(String message) {
    super(message);
  }

  public JelenideException(String message, Throwable cause) {
    super(message, cause);
  }

  public JelenideException(Throwable cause) {
    super(cause);
  }
}
