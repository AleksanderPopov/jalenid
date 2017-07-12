package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by Alex on 7/11/2017.
 */
public class FilteredJelements extends DefaultJelements {
  private final Jelements initial;
  private final JelementCondition condition;

  FilteredJelements(Jelements initial, JelementCondition condition) {
    this.initial = initial;
    this.condition = condition;
  }

  @Override
  public Collection<WebElement> find() {
    return initial.stream().filter(jelement -> condition.apply(jelement) != null).collect(toList());
  }
}
