package com.jelenide.v2.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alex on 7/9/2017.
 */
public class WebDriverRunner {
  private static Map<Thread, JelenideDriver> drivers = new ConcurrentHashMap<>();

  public synchronized static void setDriver(WebDriver driver) {
    drivers.put(Thread.currentThread(), new JelenideDriver(driver));
  }

  public synchronized static JelenideDriver getDriver() {
    return drivers.computeIfAbsent(Thread.currentThread(), k -> new JelenideDriver(new ChromeDriver()));
  }
}
