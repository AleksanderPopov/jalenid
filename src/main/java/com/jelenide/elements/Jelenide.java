package com.jelenide.elements;

import com.jelenide.elements.Jelement;
import com.jelenide.elements.Jelements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelenide {
  public static Jelement $(WebElement element) { return new Jelement(element); }

  public static Jelement $(By locator) {
    return new Jelement(locator);
  }

  public static Jelement $(String css) {
    return new Jelement(By.cssSelector(css));
  }

  public static Jelements $$(By locator) {
    return new Jelements(locator);
  }

  public static Jelements $$(Collection<WebElement> elements) {
    return new Jelements(elements);
  }

  public static Jelements $$(String css) {
    return new Jelements(By.cssSelector(css));
  }
}
