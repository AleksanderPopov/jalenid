package com.jelenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.jelenide.Jelement.findBy;
import static com.jelenide.Jelement.wrap;
import static com.jelenide.Jelements.findAll;
import static com.jelenide.Jelements.findAllTyped;
import static com.jelenide.Jelements.wrapAll;
import static com.jelenide.ReflectionTools.newInstanceWithFieldValue;
import static com.jelenide.Selectors.byCss;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;

public class Jelenide {
  public static Jelement $(String css) {
    return $(byCss(css));
  }

  public static Jelement $(By locator) {
    return findBy(locator);
  }

  public static Jelement $(WebElement element) {
    return wrap(element);
  }

  public static <T extends Jelement> T $(String css, Class<T> type) {
    return $(byCss(css), type);
  }

  public static <T extends Jelement> T $(By locator, Class<T> type) {
    return newInstanceWithFieldValue(type, "locator", locator);
  }

  public static <T extends Jelement> T $(WebElement element, Class<T> type) {
    return newInstanceWithFieldValue(type, "element", element);
  }

  public static Jelements $$(String css) {
    return findAll(byCss(css));
  }

  public static Jelements $$(By locator) {
    return findAll(locator);
  }

  public static Jelements $$(Collection<WebElement> elements) {
    return wrapAll(elements);
  }

  public static <T extends Jelement> Jelements<T> $$(String css, Class<T> type) {
    return $$(byCss(css), type);
  }

  public static <T extends Jelement> Jelements<T> $$(By locator, Class<T> type) {
    return findAllTyped(locator, type);
  }

//  public static <T extends Jelement> Jelements<T> $$(Collection<WebElement> elements, Class<T> type) {
//    return findAllTyped(elements, type);
//  }

  public static FluentWait<WebDriver> Wait() {
    return new FluentWait<>(getDriver()).withTimeout(Configuration.timeout, TimeUnit.MILLISECONDS).pollingEvery(Configuration.pollingInterval, TimeUnit.MILLISECONDS);
  }
}
