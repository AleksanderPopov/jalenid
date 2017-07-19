package com.jelenide.v2;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import com.jelenide.v2.webdriver.JelenideDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Created by Alex on 7/19/2017.
 */
public class JelenideWait {

  private final FluentWait<WebDriver> wait;

  public JelenideWait(FluentWait<WebDriver> wait) {
    this.wait = wait;
  }

  public void until(Jelement jelement, JelementCondition condition) {
    try {
      wait.until((Predicate<WebDriver>) driver -> condition.apply(jelement) != null);
    } catch (WebDriverException e) {
      throw new AssertionError(condition.errorMessage(), e);
    }
  }

  public <T extends Jelement> void until(Jelements<T> jelements, JelementsCondition<T> condition) {
    try {
      wait.until((Predicate<WebDriver>) driver -> condition.apply(jelements) != null);
    } catch (WebDriverException e) {
      throw new AssertionError(condition.errorMessage(), e);
    }
  }

  public void until(Predicate<WebDriver> condition) {
    wait.until(condition);
  }

  public void until(Function<WebDriver, Boolean> expectedCondition) {
    wait.until(expectedCondition);
  }
}
