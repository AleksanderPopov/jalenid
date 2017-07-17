package com.jelenide.v2.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class WebDriverRunner {
  private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

  public static void setDriver(WebDriver driver) {
    tldriver.set(driver);
  }

  public static WebDriver getDriver() {
    return tldriver.get();
  }
}
