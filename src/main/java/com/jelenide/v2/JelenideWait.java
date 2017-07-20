package com.jelenide.v2;

import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import com.jelenide.v2.webdriver.JelenideDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Alex on 7/19/2017.
 */
public class JelenideWait {

  private final FluentWait<WebDriver> wait;

  public JelenideWait(FluentWait<WebDriver> wait) {
    this.wait = wait;
  }

//  public void until(Jelement jelement, JelementCondition condition) {
//      wait.until((Predicate<WebDriver>) driver -> condition.apply(jelement) != null);
//  }

//  public <T extends Jelement> void until(Jelements<T> jelements, JelementsCondition<T> condition) {
//    try {
//      wait.until((Predicate<WebDriver>) driver -> condition.apply(jelements) != null);
//    } catch (WebDriverException e) {
//      throw new AssertionError(condition.errorMessage(), e);
//    }
//  }
//
//  public void until(Predicate<WebDriver> condition) {
//    wait.until(condition);
//  }
//
//  public void until(Function<WebDriver, Boolean> expectedCondition) {
//    wait.until(expectedCondition);
//  }

  public <F,S> S until(F entity, Function<F,S> condition) {
    return new FluentWait<>(entity)
            .withTimeout(4, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(WebDriverException.class)
            .until(condition);
  }
}
