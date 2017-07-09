package com.jelenide;

import org.openqa.selenium.*;

import java.util.List;

import static com.jelenide.JelementConditions.visible;
import static com.jelenide.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelement implements WebElement {
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


  public Jelement find(By locator) {
    return new Jelement(locator, this);
  }

  public Jelement find(String css) {
    return new Jelement(By.cssSelector(css), this);
  }

  public Jelements findAll(By locator) {
    return new Jelements(locator, this);
  }

  public Jelements findAll(String css) {
    return new Jelements(By.cssSelector(css), this);
  }

  public Jelement val(String value) {
    waitFor(visible()).sendKeys(value);
    return this;
  }

  public Jelement pressEnter() {
    waitFor(visible()).sendKeys(Keys.ENTER);
    return this;
  }

  private WebElement finder() {
    return contex == null ? driver.findElement(locator) : contex.findElement(locator);
  }

  private Jelement waitFor(JelementCondition condition) {

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
  public void click() {
    waitFor(visible()).click();
  }

  @Override
  public void submit() {
    finder().submit();
  }

  @Override
  public void sendKeys(CharSequence... charSequences) {
    finder().sendKeys(charSequences);
  }

  @Override
  public void clear() {
    finder().clear();
  }

  @Override
  public String getTagName() {
    return finder().getTagName();
  }

  @Override
  public String getAttribute(String s) {
    return finder().getAttribute(s);
  }

  @Override
  public boolean isSelected() {
    return finder().isSelected();
  }

  @Override
  public boolean isEnabled() {
    return finder().isEnabled();
  }

  @Override
  public String getText() {
    return finder().getText();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return finder().findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    return finder().findElement(by);
  }

  @Override
  public boolean isDisplayed() {
    return finder().isDisplayed();
  }

  @Override
  public Point getLocation() {
    return finder().getLocation();
  }

  @Override
  public Dimension getSize() {
    return finder().getSize();
  }

  @Override
  public Rectangle getRect() {
    return finder().getRect();
  }

  @Override
  public String getCssValue(String s) {
    return finder().getCssValue(s);
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return finder().getScreenshotAs(outputType);
  }
}
