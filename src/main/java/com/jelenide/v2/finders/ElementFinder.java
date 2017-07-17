package com.jelenide.v2.finders;

import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class ElementFinder implements Finder {

  private final WebElement element;
  private final List<WebElement> elements;

  public ElementFinder(WebElement element) {
    this.element = element;
    this.elements = Collections.emptyList();
  }

  public ElementFinder(List<WebElement> elements) {
    this.element = null;
    this.elements = elements;
  }

  @Override
  public WebElement find() {
    return element;
  }

  @Override
  public List<WebElement> findAll() {
    return elements;
  }
}
