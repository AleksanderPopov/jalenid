package com.jelenide.v2.webdriver;

import com.jelenide.v2.JelenideWait;
import com.jelenide.v2.conditions.Be;
import com.jelenide.v2.finders.ContextFinder;
import com.jelenide.v2.jelements.AbstractJelement;
import com.jelenide.v2.jelements.AbstractJelements;
import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.jelenide.v2.Selectors.byCss;

/**
 * Created by Alex on 7/17/2017.
 */
public class JelenideDriver implements WebDriver {

  private final WebDriver driver;
  private final JelenideWait wait;
  public final boolean takeScreenshot;


  public JelenideDriver(WebDriver driver) {
    this(driver, 4_000, 100, false);
  }

  public JelenideDriver(WebDriver driver, Capabilities capabilities) {
    this(
            driver,
            capabilities.getCapability("timeout") != null ? Long.valueOf(capabilities.getCapability("timeout").toString()) :  4_000,
            capabilities.getCapability("pollingInterval") != null ? Long.valueOf(capabilities.getCapability("pollingInterval").toString()) :  100,
            capabilities.getCapability("takeScreenshot") != null ? Boolean.valueOf(capabilities.getCapability("takeScreenshot").toString()) : false
    );
  }

  public JelenideDriver(WebDriver driver, long timeout, long pollingInterval, boolean takeScreenshot) {
    this.driver = driver;
    this.takeScreenshot = takeScreenshot;
    this.wait = new JelenideWait(new FluentWait<>(driver).withTimeout(timeout, TimeUnit.MILLISECONDS).pollingEvery(pollingInterval, TimeUnit.MILLISECONDS));
  }

  public JelenideDriver open(String url) {
    driver.get(url);
    return this;
  }

  public JelenideDriver maximize() {
    this.driver.manage().window().maximize();
    return this;
  }

  public Jelement $(String css) {
    return $(byCss(css));
  }

  public Jelement $(By locator) {
    return new AbstractJelement(new ContextFinder(locator, driver), this);
  }

  public Jelements<Jelement> $$(String css) {
    return $$(byCss(css));
  }

  public Jelements<Jelement> $$(By locator) {
    return new AbstractJelements(new ContextFinder(locator, driver), this);
  }

  public JelenideWait Wait() {
    return wait;
  }

  public static void main(String[] args) {

    JelenideDriver driver = new JelenideDriver(null);
    driver.Wait().until(driver.$(""), Be.visible());
  }

  @Override
  public void get(String s) {
    driver.get(s);
  }

  @Override
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public String getTitle() {
    return driver.getTitle();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    return driver.findElement(by);
  }

  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

  @Override
  public void close() {
    driver.close();
  }

  @Override
  public void quit() {
    driver.quit();
  }

  @Override
  public Set<String> getWindowHandles() {
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }

}
