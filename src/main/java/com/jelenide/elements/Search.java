package com.jelenide.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alex on 7/12/2017.
 */
public class Search extends Jelement {
  Search(String css) {
    this(By.cssSelector(css));
  }

  Search(By locator) {
    super(locator);
  }

  Search(WebElement element) {
    super(element);
  }

  public void search(String text) {
    this.
  }
}
