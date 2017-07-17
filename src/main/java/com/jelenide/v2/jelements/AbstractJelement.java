package com.jelenide.v2.jelements;

import com.jelenide.v2.Configuration;
import com.jelenide.v2.conditions.Be;
import com.jelenide.v2.conditions.JelementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class AbstractJelement implements Jelement {

  private final Finder finder;

  public AbstractJelement(Finder finder) {
    this.finder = finder;
  }

  @Override
  public WebElement toWebElement() {
    return finder.find();
  }

  @Override
  public Jelement find(By locator) {
    return new AbstractJelement(new ContextFinder(locator, this));
  }

  @Override
  public Jelements findAll(By locator) {
    return new AbstractJelements(new ContextFinder(locator, this));
  }

  @Override
  public Jelement val(String text) {
    waitFor(Be.visible());
    toWebElement().clear();
    toWebElement().sendKeys(text);
    return this;
  }

  @Override
  public Jelement pressEnter() {
    waitFor(Be.visible());
    toWebElement().sendKeys(Keys.ENTER);
    return this;
  }

  @Override
  public void click() {
    waitFor(Be.visible());
    toWebElement().click();
  }

  @Override
  public Jelement should(JelementCondition condition) {
    waitFor(condition);
    return this;
  }

  @Override
  public List<WebElement> findElements(By locator) {
    return toWebElement().findElements(locator);
  }

  @Override
  public WebElement findElement(By locator) {
    return toWebElement().findElement(locator);
  }

  private void waitFor(JelementCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    do {
      if (condition.apply(this) != null)
        return;
    } while (System.currentTimeMillis() <= endTime);

    throw new AssertionError("Jelements does not meet the condition");
  }

}
