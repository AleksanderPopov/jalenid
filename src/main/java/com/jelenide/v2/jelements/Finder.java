package com.jelenide.v2.jelements;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public interface Finder {

  WebElement find();
  List<WebElement> findAll();

}
