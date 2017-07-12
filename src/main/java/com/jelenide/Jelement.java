package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Alex on 7/9/2017.
 */
public interface Jelement extends WebElement {

  Jelement find(String css);

  Jelement find(By locator);

  Jelements findAll(String css);

  Jelements findAll(By locator);

  WebElement find();

  Jelement shouldHave(JelementCondition condition);

  Jelement shouldBe(JelementCondition condition);

  Jelement val(String value);

  Jelement pressEnter();
}
