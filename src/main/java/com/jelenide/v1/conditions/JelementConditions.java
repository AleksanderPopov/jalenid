package com.jelenide.v1.conditions;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementConditions {

  public static JelementCondition visible() {
    return doIgnoreException(WebElement::isDisplayed);
  }

  public static JelementCondition hidden() {
    return doIgnoreException(j -> !j.isDisplayed());
  }

  public static JelementCondition text(String text) {
    return doIgnoreException(jelement -> text.trim().equalsIgnoreCase(jelement.getText().trim()));
  }

  private static JelementCondition doIgnoreException(Predicate<WebElement> predicate) {
    return jelement -> {
      try {
        return predicate.test(jelement) ? jelement : null;
      } catch (WebDriverException e) {
        return null;
      }
    };
  }
}
