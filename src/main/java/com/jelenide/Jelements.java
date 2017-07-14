package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import com.jelenide.conditions.JelementsCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import static com.jelenide.Jelement.wrap;
import static com.jelenide.ReflectionTools.newInstanceWithFieldValue;
import static com.jelenide.Selectors.byCss;
import static com.jelenide.conditions.JelementsConditions.sizeGreaterThan;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;
import static java.util.stream.Collectors.toList;

public class Jelements<T extends Jelement> extends AbstractCollection<T> {
  private final By locator;
  private final Jelement contex;
  private final Collection<WebElement> elements;
  private final Class<T> type;

  protected Jelements(Jelements<T> initial) {
    this(initial.locator, initial.contex, initial.type, initial.elements);
  }

  private Jelements(By locator, Jelement contex, Class<T> clazz, Collection<WebElement> elements) {
    this.locator = locator;
    this.contex = contex;
    this.elements = elements;
    this.type = clazz;
  }

  static <T extends Jelement> Jelements<T> getAll(String css) {
    return getAll(byCss(css));
  }

  static <T extends Jelement> Jelements<T> getAll(By locator) {
    return new Jelements<>(locator, null, null, null);
  }

  static <T extends Jelement> Jelements<T> getAllInContex(By locator, Jelement contex) {
    return new Jelements<>(locator, contex, null, null);
  }

  static <T extends Jelement> Jelements<T> wrapAll(Collection<WebElement> elements) {
    return new Jelements<>(null, null, null, elements);
  }

  static <T extends Jelement> Jelements<T> getAllTyped(String css, Class<T> type) {
    return getAllTyped(byCss(css), type);
  }

  static <T extends Jelement> Jelements<T> getAllTyped(By locator, Class<T> type) {
    return new Jelements<>(locator, null, type, null);
  }

  static <T extends Jelement> Jelements<T> wrapAllTyped(Collection<WebElement> elements, Class<T> type) {
    return new Jelements<>(null, null, type, elements);
  }

  public Jelements<T> filter(JelementCondition condition) {
    return FilteredJelements.of(this, condition);
  }

  public Jelements<T> shouldHave(JelementsCondition<T> condition) {
    return waitFor(condition);
  }

  public T first() {
    return get(0);
  }

  public T get(int index) {
    return this.shouldHave(sizeGreaterThan(index + 1))
            .stream()
            .map(jelement -> type != null ? newInstanceWithFieldValue(type, "element", jelement) : jelement)
            .collect(toList())
            .get(index);
  }

  public Collection<WebElement> find() {
    return elements != null ? elements :
            contex != null ? contex.findElements(locator) : getDriver().findElements(locator);
  }

  private Jelements<T> waitFor(JelementsCondition<T> condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    while (true) {
      if (condition.apply(this) != null)
        return this;
      if (System.currentTimeMillis() > endTime)
        throw new AssertionError("element " + locator + " is not " + condition);
    }
  }

  @Override
  public Iterator<T> iterator() {
    return find().stream().map(we -> (T) wrap(we)).collect(toList()).iterator();
  }

  @Override
  public int size() {
    return find().size();
  }

}
