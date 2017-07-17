package com.jelenide.v1;

import com.jelenide.v1.conditions.JelementCondition;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class FilteredJelements<T extends Jelement> extends Jelements<T> {
  private final Jelements<T> initial;
  private final JelementCondition condition;

  private FilteredJelements(Jelements<T> initial, JelementCondition condition) {
    super(initial);
    this.initial = initial;
    this.condition = condition;
  }

  static <T extends Jelement> FilteredJelements<T> of(Jelements<T> initial, JelementCondition condition) {
    return new FilteredJelements<>(initial, condition);
  }

  @Override
  public Collection<WebElement> find() {
    return initial.find().stream().filter(element -> condition.apply(element) != null).collect(toList());
  }
}
