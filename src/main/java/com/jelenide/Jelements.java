package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import com.jelenide.conditions.JelementsCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import static com.jelenide.ReflectionTools.setFieldValue;
import static com.jelenide.conditions.JelementsConditions.sizeGreaterThan;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;
import static java.util.stream.Collectors.toList;

/**
 * Created by apop on 7/12/2017.
 */
public class Jelements<T extends Jelement> extends AbstractCollection<T> {
  private final By locator;
  private final Jelement contex;
  private final Collection<? extends WebElement> cachedElements;
  protected final Class<T> type;

  static <T extends Jelement> Jelements<T> by(By locator) {
    return new Jelements<T>(locator, null, null, null);
  }

  static <T extends Jelement> Jelements<T> typed(By locator, Class<T> type) {
    return new Jelements<T>(locator, null, type, null);
  }

  static <T extends Jelement> Jelements<T> wrap(Collection<WebElement> elements) {
    return new Jelements<T>(null, null, null, elements);
  }

  static <T extends Jelement> Jelements<T> fromContext(By locator, Jelement contex) {
    return new Jelements<T>(locator, contex, null, null);
  }

  protected Jelements(Jelements<T> initial, Class<T> type) { this(null, null, type, initial); }

  protected Jelements(By locator, Jelement contex, Class<T> clazz, Collection<? extends WebElement> elements) {
    this.locator = locator;
    this.contex = contex;
    this.cachedElements = elements;
    this.type = clazz;
  }

  public Jelements<T> filter(JelementCondition condition) {
    return type != null ? FilteredJelements.typed(this, condition, type) : FilteredJelements.of(this, condition);
  }

  public Jelements<T> shouldHave(JelementsCondition condition) {
    return waitFor(condition);
  }

  public T first() {
    return get(0);
  }

  public T get(int index) {
      return this.shouldHave(sizeGreaterThan(index + 1))
              .stream()
              .map(jelement -> {
                if(type != null) {
                  T t = newInstance(type);
                  setFieldValue(t, "cachedElement", jelement);
                  return t;
                }
                return jelement;
              })
              .collect(toList())
              .get(index);
  }

  public static <T extends Jelement> T newInstance(Class<T> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  public Collection<? extends WebElement> find() {
    return cachedElements != null ? cachedElements :
            contex != null ? contex.findElements(locator) : getDriver().findElements(locator);
  }

  private Jelements<T> waitFor(JelementsCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    while (true) {
      try {
        Jelements<T> result = (Jelements<T>) condition.apply(this);
        if (result != null)
          return result;
      } catch (WebDriverException e) {/*NOP*/}
      if (System.currentTimeMillis() > endTime)
        throw new AssertionError("element " + locator + " is not " + condition);
    }
  }

  @Override
  public Iterator<T> iterator() {
    return find().stream().map(we -> (T) new Jelement(we)).collect(toList()).iterator();
  }

  @Override
  public int size() {
    return find().size();
  }

}
