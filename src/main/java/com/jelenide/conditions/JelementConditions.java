package com.jelenide.conditions;

import com.jelenide.elements.Jelement;
import org.openqa.selenium.WebDriverException;

import java.util.function.Predicate;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementConditions {

  public static JelementCondition visible() {
    return doIgnoreException((Jelement::isDisplayed));
  }

  public static JelementCondition text(String text) {
    return doIgnoreException(jelement -> text.trim().equalsIgnoreCase(jelement.getText().trim()));
  }

  private static JelementCondition doIgnoreException(Predicate<Jelement> predicate) {
    return jelement -> {
      try {
        return predicate.test(jelement) ? jelement : null;
      } catch (WebDriverException e) {
        return null;
      }
    };
  }
}
