package com.jelenide.v2.jelements;

import com.jelenide.v2.Configuration;
import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class AbstractJelements implements Jelements {

  private final Finder finder;

  public AbstractJelements(Finder finder) {
    this.finder = finder;
  }

  @Override
  public Jelements should(JelementsCondition condition) {
    waitFor(condition);
    return this;
  }

  @Override
  public Jelements filter(JelementCondition condition) {
    return new AbstractJelements(new ConditionalFinder(condition, this));
  }

  @Override
  public Jelement get(int index) {
    this.should(Have.sizeGreaterThanOrEqual(index + 1));
    return new AbstractJelement(new ElementFinder(finder.findAll().get(index)));
  }

  @Override
  public Jelement first() {
    return get(0);
  }

  @Override
  public List<WebElement> toWebElements() {
    return finder.findAll();
  }

  private void waitFor(JelementsCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    do {
      if (condition.apply(this) != null)
        return;
    } while (System.currentTimeMillis() <= endTime);

    throw new AssertionError("Jelements does not meet the condition");
  }

}
