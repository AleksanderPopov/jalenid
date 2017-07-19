package com.jelenide.v2.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class WebDriverRunner {
  private static ThreadLocal<JelenideDriver> jelenideDriverThreadLocal = new ThreadLocal<>();

  public synchronized static JelenideDriver getDriver() {
    JelenideDriver driver = jelenideDriverThreadLocal.get();
    if (driver == null) {
      driver = new JelenideDriver(new ChromeDriver());
      setDriver(driver);
    }
    return driver;
  }

  public synchronized static void setDriver(JelenideDriver driver) {
    jelenideDriverThreadLocal.set(driver);
  }
}
