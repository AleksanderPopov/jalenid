package com.jelenide.v2.exceptions;

/**
 * Created by apop on 7/20/2017.
 */
public class ConditionFailedException extends JelenideException {
  public ConditionFailedException() {
    super();
  }

  public ConditionFailedException(String message) {
    super(message);
  }

  public ConditionFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConditionFailedException(Throwable cause) {
    super(cause);
  }
}
