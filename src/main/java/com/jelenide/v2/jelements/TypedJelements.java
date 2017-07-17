package com.jelenide.v2.jelements;

import com.jelenide.v2.ReflectionTools;
import com.jelenide.v2.Configuration;
import com.jelenide.v2.conditions.Have;
import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import com.jelenide.v2.finders.ConditionalFinder;
import com.jelenide.v2.finders.ElementFinder;
import com.jelenide.v2.finders.Finder;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex on 7/17/2017.
 */
public class TypedJelements<T extends Jelement> implements Jelements<T> {

  private final Class<T> type;
  private final Finder finder;

  public TypedJelements(Finder finder, Class<T> type) {
    this.finder = finder;
    this.type = type;
  }

  @Override
  public Jelements<T> should(JelementsCondition<T> condition) {
    waitFor(condition);
    return this;
  }

  @Override
  public Jelements<T> filter(JelementCondition condition) {
    return new TypedJelements<>(new ConditionalFinder<>(condition, this), type);
  }

  @Override
  public T get(int index) {
    this.should(Have.sizeGreaterThanOrEqual(index + 1));
    return ReflectionTools.newInstance(type, Finder.class, new ElementFinder(finder.findAll().get(index)));
  }

  @Override
  public T first() {
    return get(0);
  }

  @Override
  public <S extends Jelement> Jelements<S> as(Class<S> clazz) {
    return new TypedJelements<>(finder, clazz);
  }

  @Override
  public List<WebElement> toWebElements() {
    return finder.findAll();
  }

  private void waitFor(JelementsCondition<T> condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    do {
      if (condition.apply(this) != null)
        return;
    } while (System.currentTimeMillis() <= endTime);

    throw new AssertionError("Jelements does not meet the condition");
  }
}
