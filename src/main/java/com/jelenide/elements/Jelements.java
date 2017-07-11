package com.jelenide.elements;

import com.jelenide.Configuration;
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
 * Created by Alex on 7/10/2017.
 */
public class Jelements extends AbstractCollection<Jelement> {
  private final By locator;
  private final Jelement contex;
  private final Collection<WebElement> cachedElements;

  Jelements() {
    this(null, null, null);
  }

  Jelements(By locator) {
    this(locator, null, null);
  }

  Jelements(By locator, Jelement contex) {
    this(locator, contex, null);
  }

  Jelements(Collection<WebElement> elements) {
    this(null, null, elements);
  }

  private Jelements(By locator, Jelement contex, Collection<WebElement> elements) {
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
    return this.shouldHave(JelementsConditions.sizeGreaterThan(1)).stream().findFirst().orElseThrow(AssertionError::new);
  }

  protected Collection<WebElement> find() {
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
    return find().stream().map(Jelement::new).collect(toList()).iterator();
  }

  @Override
  public int size() {
    return find().size();
  }
}
