package com.jelenide;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.jelenide.Conditions.visible;
import static com.jelenide.Helper.pause;
import static com.jelenide.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelement {
  private final By locator;
  private final Jelement contex;
  private final WebDriver driver;

  public Jelement(By locator) {
    this(locator, null);
  }

  private Jelement(By locator, Jelement contex) {
    this.locator = locator;
    this.contex = contex;
    this.driver = getDriver();
  }

  public Jelement shouldBe(Condition condition) {

  }

  public Jelement find(By locator) {
    return new Jelement(locator, this);
  }

  public Jelement find(String css) {
    return new Jelement(By.cssSelector(css), this);
  }

  public Jelement val(String value) {
    waitUntil(visible()).sendKeys(value);
    return this;
  }

  public Jelement pressEnter() {
    waitUntil(visible()).sendKeys(Keys.ENTER);
    return this;
  }

  private WebElement waitUntil() {
    WebElement result = null;
    for (int i = 0; i < 20; i++) {
      try {
        result = contex == null ? driver.findElement(locator) : contex.waitUntil().findElement(locator);
      } catch (Exception e) {
      }
      if (result != null) return result;
      else pause(500);
    }

    throw new AssertionError("element " + locator + " is not present");
  }

  private WebElement waitUntil(Condition condition) {

    WebElement result;
    for (int i = 0; i < 20; i++) {
      try {
        if (contex == null) {
          WebElement element = driver.findElement(locator);
          result = condition.apply(element);
        } else {
          WebElement element = contex.waitUntil().findElement(locator);
          result = condition.apply(element);
        }
      } catch (Exception e) {
        result = null;
      }
      if (result != null) return result;
      else pause(500);
    }

    throw new AssertionError("element " + locator + " is not " + condition);
  }

}
