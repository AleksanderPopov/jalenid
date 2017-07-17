package com.jelenide.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.jelenide.v1.webdriver.WebDriverRunner.getDriver;

public class Jelenide {
  public static Jelement $(String css) {
    return Jelement.get(css);
  }

  public static Jelement $(By locator) {
    return Jelement.get(locator);
  }

  public static Jelement $(WebElement element) {
    return Jelement.wrap(element);
  }

  public static <T extends Jelement> T $(String css, Class<T> type) {
    return Jelement.getTyped(css, type);
  }

  public static <T extends Jelement> T $(By locator, Class<T> type) {
    return Jelement.getTyped(locator, type);
  }

  public static <T extends Jelement> T $(WebElement element, Class<T> type) {
    return Jelement.wrapTyped(element, type);
  }

  public static Jelements<Jelement> $$(String css) {
    return Jelements.getAll(css);
  }

  public static Jelements<Jelement> $$(By locator) {
    return Jelements.getAll(locator);
  }

  public static Jelements<Jelement> $$(Collection<WebElement> elements) {
    return Jelements.wrapAll(elements);
  }

  public static <T extends Jelement> Jelements<T> $$(String css, Class<T> type) {
    return Jelements.getAllTyped(css, type);
  }

  public static <T extends Jelement> Jelements<T> $$(By locator, Class<T> type) {
    return Jelements.getAllTyped(locator, type);
  }

  public static <T extends Jelement> Jelements<T> $$(Collection<WebElement> elements, Class<T> type) {
    return Jelements.wrapAllTyped(elements, type);
  }

  public static FluentWait<WebDriver> Wait() {
    return new FluentWait<>(getDriver()).withTimeout(Configuration.timeout, TimeUnit.MILLISECONDS).pollingEvery(Configuration.pollingInterval, TimeUnit.MILLISECONDS);
  }

}
