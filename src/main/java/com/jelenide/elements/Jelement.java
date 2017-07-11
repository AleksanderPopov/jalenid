package com.jelenide.elements;

import com.jelenide.Configuration;
import com.jelenide.conditions.JelementCondition;
import org.openqa.selenium.*;

import java.util.List;

import static com.jelenide.conditions.JelementConditions.visible;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelement implements WebElement {
  private final By locator;
  private final Jelement contex;
  private final WebElement cachedElement;

  Jelement(By locator) {
    this(locator, null, null);
  }

  Jelement(WebElement element) { this(null, null, element); }

  private Jelement(By locator, Jelement contex) {
    this(locator, contex, null);
  }

  private Jelement(By locator, Jelement contex, WebElement initialElement) {
    this.locator = locator;
    this.contex = contex;
    this.cachedElement = initialElement;
  }

  public Jelement find(String css) {
    return find(By.cssSelector(css));
  }

  public Jelement find(By locator) {
    return new Jelement(locator, this);
  }

  public Jelements findAll(By locator) {
    return new Jelements(locator, this);
  }

  public Jelement shouldHave(JelementCondition condition) {
    return condition.apply(this);
  }

  public Jelement val(String value) {
    waitFor(visible()).sendKeys(value);
    return this;
  }

  public Jelement pressEnter() {
    waitFor(visible()).sendKeys(Keys.ENTER);
    return this;
  }

  private WebElement find() {
    return cachedElement != null ? cachedElement :
            contex != null ? contex.findElement(locator): getDriver().findElement(locator);
  }

  private WebElement waitFor(JelementCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    while (true) {
      try {
        Jelement result = condition.apply(this);
        if (result != null)
          return result.find();
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
    find().submit();
  }

  @Override
  public void sendKeys(CharSequence... charSequences) {
    find().sendKeys(charSequences);
  }

  @Override
  public void clear() {
    find().clear();
  }

  @Override
  public String getTagName() {
    return find().getTagName();
  }

  @Override
  public String getAttribute(String s) {
    return find().getAttribute(s);
  }

  @Override
  public boolean isSelected() {
    return find().isSelected();
  }

  @Override
  public boolean isEnabled() {
    return find().isEnabled();
  }

  @Override
  public String getText() {
    return find().getText();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return find().findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    return find().findElement(by);
  }

  @Override
  public boolean isDisplayed() {
    return find().isDisplayed();
  }

  @Override
  public Point getLocation() {
    return find().getLocation();
  }

  @Override
  public Dimension getSize() {
    return find().getSize();
  }

  @Override
  public Rectangle getRect() {
    return find().getRect();
  }

  @Override
  public String getCssValue(String s) {
    return find().getCssValue(s);
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return find().getScreenshotAs(outputType);
  }
}
