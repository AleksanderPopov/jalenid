package com.jelenide.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.jelenide.webdriver.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/11/2017.
 */
public class RootElement {
  private WebDriver driver;

  RootElement() {
    this.driver = getDriver();
  }

  List<WebElement> findElements(By locator) {
    return driver.findElements(locator);
  }

  WebElement findElement(By locator) {
    return driver.findElement(locator);
  }
}
