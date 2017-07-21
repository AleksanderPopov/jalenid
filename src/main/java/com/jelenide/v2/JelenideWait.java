package com.jelenide.v2;

import com.jelenide.v2.exceptions.JelenideException;
import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Alex on 7/19/2017.
 */
public class JelenideWait {

  private final WebDriver driver;
  private final long timeout;
  private final long pollingInterval;

  public JelenideWait(WebDriver driver, long timeout, long pollingInterval) {
    this.driver = driver;
    this.timeout = timeout;
    this.pollingInterval = pollingInterval;
  }

  public Boolean until(Function<WebDriver, Boolean> expectedCondition) {
    return until(driver, expectedCondition);
  }

  public <F, S> S until(F entity, Function<F, S> condition) {
    return new FluentWait<>(entity)
            .withTimeout(timeout, TimeUnit.MILLISECONDS)
            .pollingEvery(pollingInterval, TimeUnit.MILLISECONDS)
            .ignoring(WebDriverException.class)
            .ignoring(JelenideException.class)
            .until(condition);
  }
}
