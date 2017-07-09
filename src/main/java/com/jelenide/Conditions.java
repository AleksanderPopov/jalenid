package com.jelenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alex on 7/9/2017.
 */
public class Conditions {

  public static Condition visible() {
    return new Condition() {
      @Override
      public WebElement apply(By webElement) {
        try {
          return webElement.isDisplayed() ? webElement : null;
        } catch (Exception e) {
          return null;
        }
      }
    };
  }
}
