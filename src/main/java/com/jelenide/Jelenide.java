package com.jelenide;

import org.openqa.selenium.By;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelenide {
  public static Jelement $(By locator) {
    return new Jelement(locator);
  }

  public static Jelement $(String css) {
    return new Jelement(By.cssSelector(css));
  }
}
