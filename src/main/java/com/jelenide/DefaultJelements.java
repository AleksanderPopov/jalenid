package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import com.jelenide.conditions.JelementsCondition;
import com.jelenide.conditions.JelementsConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import static com.jelenide.webdriver.WebDriverRunner.getDriver;
import static java.util.stream.Collectors.toList;

/**
 * Created by apop on 7/12/2017.
 */
public class DefaultJelements extends AbstractCollection<Jelement> implements Jelements {
  private final By locator;
  private final Jelement contex;
  private final Collection<WebElement> cachedElements;

  DefaultJelements() {
    this(null, null, null);
  }

  DefaultJelements(By locator) {
    this(locator, null, null);
  }

  DefaultJelements(By locator, Jelement contex) {
    this(locator, contex, null);
  }

  DefaultJelements(Collection<WebElement> elements) {
    this(null, null, elements);
  }

  private DefaultJelements(By locator, Jelement contex, Collection<WebElement> elements) {
    this.locator = locator;
    this.contex = contex;
    this.cachedElements = elements;
  }

  public Jelements filter(JelementCondition condition) {
    return new FilteredJelements(this, condition);
  }

  public Jelements shouldHave(JelementsCondition condition) {
    return waitFor(condition);
  }

  public Jelement first() {
    return get(0);
  }

  public Jelement get(int index) {
    return this.shouldHave(JelementsConditions.sizeGreaterThan(index + 1)).toArray(new Jelement[0])[index];
  }

  public Collection<WebElement> find() {
    return cachedElements != null ? cachedElements :
            contex != null ? contex.findElements(locator) : getDriver().findElements(locator);
  }

  private Jelements waitFor(JelementsCondition condition) {

    long endTime = System.currentTimeMillis() + Configuration.timeout;

    while (true) {
      try {
        Jelements result = condition.apply(this);
        if (result != null)
          return result;
      } catch (WebDriverException e) {/*NOP*/}
      if (System.currentTimeMillis() > endTime)
        throw new AssertionError("element " + locator + " is not " + condition);
    }
  }

  @Override
  public Iterator<Jelement> iterator() {
    return find().stream().map(we -> (Jelement) new DefaultJelement(we)).collect(toList()).iterator();
  }

  @Override
  public int size() {
    return find().size();
  }

}
