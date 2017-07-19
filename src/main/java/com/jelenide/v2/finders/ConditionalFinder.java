package com.jelenide.v2.finders;

import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.jelements.AbstractJelement;
import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import com.jelenide.v2.webdriver.JelenideDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Alex on 7/17/2017.
 */
public class ConditionalFinder<T extends Jelement> implements Finder {

  private final JelementCondition condition;
  private final Jelements<T> jelements;
  private final JelenideDriver driver;

  public ConditionalFinder(JelementCondition condition, Jelements<T> jelements, JelenideDriver driver) {
    this.condition = condition;
    this.jelements = jelements;
    this.driver = driver;
  }

  @Override
  public WebElement find() {
    return this.findAll().get(0);
  }

  @Override
  public List<WebElement> findAll() {
    return jelements.toWebElements()
            .stream()
            .map(webelement -> new AbstractJelement(new ElementFinder(webelement), driver))
            .filter(jelement -> condition.apply(jelement) != null)
            .map(AbstractJelement::toWebElement)
            .collect(toList());
  }
}
