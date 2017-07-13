package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by Alex on 7/11/2017.
 */
public class FilteredJelements<T extends Jelement> extends Jelements<T> {
  private final Jelements<T> initial;
  private final JelementCondition condition;

  protected FilteredJelements(Jelements<T> initial, JelementCondition condition, Class<T> type) {
    super(initial, type);
    this.initial = initial;
    this.condition = condition;
  }

  static <T extends Jelement> FilteredJelements<T> of(Jelements<T> initial, JelementCondition condition) {
    return new FilteredJelements<>(initial, condition, null);
  }

  static <T extends Jelement> FilteredJelements<T> typed(Jelements<T> initial, JelementCondition condition, Class<T> type) {
    return new FilteredJelements<>(initial, condition, type);
  }

  @Override
  public Collection<WebElement> find() {
    return initial.stream().filter(jelement -> condition.apply(jelement) != null).collect(toList());
  }
}
