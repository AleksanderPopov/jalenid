package com.jelenide.v2;

import com.jelenide.v2.finders.ContextFinder;
import com.jelenide.v2.jelements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static com.jelenide.v2.Selectors.byCss;
import static com.jelenide.v2.webdriver.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/17/2017.
 */
public class Jelenide {

  public static Jelement $(String css) {
    return $(byCss(css));
  }

  public static Jelement $(By locator) {
    return new AbstractJelement(new ContextFinder(locator, getDriver()));
  }

  public static Jelements<Jelement> $$(String css) {
    return $$(byCss(css));
  }

  public static Jelements<Jelement> $$(By locator) {
    return new AbstractJelements(new ContextFinder(locator, getDriver()));
  }

  public static FluentWait<? extends WebDriver> Wait() {
    return new FluentWait<>(getDriver())
            .withTimeout(getDriver().timeout, TimeUnit.MILLISECONDS)
            .pollingEvery(getDriver().pollingInterval, TimeUnit.MILLISECONDS);
  }

}
