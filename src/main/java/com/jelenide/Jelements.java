package com.jelenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;

import static com.jelenide.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/10/2017.
 */
public class Jelements extends AbstractCollection<Jelement> {
  private final By locator;
  private final WebDriver driver;
  private final Jelement contex;

  public Jelements(By locator) {
    this.locator = locator;
    this.contex = null;
    this.driver = getDriver();
  }

  public Jelements(By locator, Jelement contex) {
    this.locator = locator;
    this.contex = contex;
    this.driver = getDriver();
  }

  private List<WebElement> finder() {
    return contex == null ? driver.findElements(locator) : contex.findElements(locator);
  }

  private Jelements waitFor(JelementsCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    while (true) {
      try {
        Jelement result = condition.apply(this);
        if (result != null)
          return result;
      } catch (WebDriverException e) {/*NOP*/}
      if (System.currentTimeMillis() > endTime)
        throw new AssertionError("element " + locator + " is not " + condition);
    }
  }

  @Override
  public Iterator<Jelement> iterator() {
    return null;
  }

  @Override
  public int size() {
    return finder().size();
  }
}
