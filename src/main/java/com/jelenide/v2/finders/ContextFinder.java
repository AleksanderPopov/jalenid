package com.jelenide.v2.finders;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class ContextFinder implements Finder {

  private final By locator;
  private final SearchContext contex;

  public ContextFinder(By locator, SearchContext context) {
    this.locator = locator;
    this.contex = context;
  }

  @Override
  public WebElement find() {
    return contex.findElement(locator);
  }

  @Override
  public List<WebElement> findAll() {
    return contex.findElements(locator);
  }
}
