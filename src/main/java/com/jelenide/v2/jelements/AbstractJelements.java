package com.jelenide.v2.jelements;

import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import com.jelenide.v2.finders.ConditionalFinder;
import com.jelenide.v2.finders.ElementFinder;
import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.webdriver.JelenideDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class AbstractJelements implements Jelements<Jelement> {

  private final Finder finder;
  private final JelenideDriver driver;

  public AbstractJelements(Finder finder, JelenideDriver driver) {
    this.finder = finder;
    this.driver = driver;
  }

  @Override
  public Jelements<Jelement> should(JelementsCondition<Jelement> condition) {
    driver.Wait().until(this, condition);
    return this;
  }

  @Override
  public Jelements<Jelement> filter(JelementCondition condition) {
    return new AbstractJelements(new ConditionalFinder<>(condition, this, driver), driver);
  }

  @Override
  public Jelement get(int index) {
    this.should(Have.sizeGreaterThanOrEqual(index + 1));
    return new AbstractJelement(new ElementFinder(finder.findAll().get(index)), driver);
  }

  @Override
  public Jelement first() {
    return get(0);
  }

  @Override
  public <S extends Jelement> Jelements<S> as(Class<S> clazz) {
    return new TypedJelements<>(finder, driver, clazz);
  }

  @Override
  public List<WebElement> toWebElements() {
    return finder.findAll();
  }


}
