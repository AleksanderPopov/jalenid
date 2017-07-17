package com.jelenide.v2.jelements;

import com.jelenide.v2.conditions.JelementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by Alex on 7/17/2017.
 */
public interface Jelement extends SearchContext {


  Jelements findAll(By locator);

  Jelement find(By locator);

  Jelement should(JelementCondition condition);

  Jelement val(String value);

  Jelement pressEnter();

  void click();

  <T extends Jelement> T as(Class<T> clazz);

  WebElement toWebElement();

}
