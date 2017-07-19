package com.jelenide.v2.jelements;

import com.jelenide.v2.Configuration;
import com.jelenide.v2.ReflectionTools;
import com.jelenide.v2.conditions.Be;
import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.finders.ContextFinder;
import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.webdriver.JelenideDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class AbstractJelement implements Jelement {

  private final Finder finder;
  private final JelenideDriver driver;

  public AbstractJelement(Finder finder, JelenideDriver driver) {
    this.finder = finder;
    this.driver = driver;
  }

  @Override
  public WebElement toWebElement() {
    return finder.find();
  }

  @Override
  public Jelement find(By locator) {
    return new AbstractJelement(new ContextFinder(locator, this), driver);
  }

  @Override
  public Jelements findAll(By locator) {
    return new AbstractJelements(new ContextFinder(locator, this), driver);
  }

  @Override
  public Jelement val(String text) {
    should(Be.visible());
    toWebElement().clear();
    toWebElement().sendKeys(text);
    return this;
  }

  @Override
  public Jelement pressEnter() {
    should(Be.visible());
    toWebElement().sendKeys(Keys.ENTER);
    return this;
  }

  @Override
  public void click() {
    should(Be.visible());
    toWebElement().click();
  }

  @Override
  public <T extends Jelement> T as(Class<T> clazz) {
    return ReflectionTools.newInstance(clazz, Finder.class, finder);
  }

  @Override
  public Jelement should(JelementCondition condition) {
    driver.Wait().until(this, condition);
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
