package com.jelenide.v1;

import org.openqa.selenium.By;

public class Selectors {

  public static By byCss(String css) { return By.cssSelector(css); }

  public static By byXpath(String xpath) { return By.xpath(xpath); }

  public static By byId(String id) { return By.id(id); }

}
