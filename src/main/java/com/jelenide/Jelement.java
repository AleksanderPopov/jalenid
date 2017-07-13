package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import org.openqa.selenium.*;

import java.util.List;

import static com.jelenide.Selectors.byCss;
import static com.jelenide.conditions.JelementConditions.visible;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;

/**
 * Created by apop on 7/12/2017.
 */
public class Jelement implements WebElement {
  private final By locator;
  private final Jelement contex;
  private final WebElement element;

  protected Jelement() {
    this(null, null, null);
  }

  Jelement(By locator) {
    this(locator, null, null);
  }

  Jelement(WebElement element) { this(null, null, element); }

  private Jelement(By locator, Jelement contex) {
    this(locator, contex, null);
  }

  private Jelement(By locator, Jelement contex, WebElement element) {
    this.locator = locator;
    this.contex = contex;
    this.element = element;
  }

  public Jelement find(String css) {
    return find(byCss(css));
  }

  public Jelement find(By locator) {
    return new Jelement(locator, this);
  }

  public Jelements findAll(String css) {
    return findAll(byCss(css));
  }

  public Jelements findAll(By locator) {
    return Jelements.fromContext(locator, this);
  }

  public Jelement shouldHave(JelementCondition condition) {
    return new Jelement(waitFor(condition));
  }

  public Jelement shouldBe(JelementCondition condition) {
    return shouldHave(condition);
  }

  public Jelement val(String value) {
    this.shouldBe(visible()).clear();
    this.shouldBe(visible()).sendKeys(value);
    return this;
  }

  public Jelement pressEnter() {
    this.shouldBe(visible()).sendKeys(Keys.ENTER);
    return this;
  }

  public WebElement find() {
    return element != null ? element :
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
        throw new AssertionError("element located " + locator + " is not " + condition);
    }
  }

  @Override
  public void click() {
    waitFor(visible()).click();
  }

  @Override
  public void submit() {
    waitFor(visible()).submit();
  }

  @Override
  public void sendKeys(CharSequence... charSequences) {
    waitFor(visible()).sendKeys(charSequences);
  }

  @Override
  public void clear() {
    waitFor(visible()).clear();
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

  @Override
  public boolean equals(Object o) {
    return find().equals(o);
  }

  @Override
  public int hashCode() {
    return find().hashCode();
  }

  @Override
  public String toString() {
    return find().toString();
  }
}
