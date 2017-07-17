package com.jelenide.v2.jelements;

import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Jelements<T extends Jelement> {

  Jelements<T> should(JelementsCondition<T> condition);

  Jelements<T> filter(JelementCondition condition);

  T get(int index);

  T first();

  <S extends Jelement> Jelements<S> as(Class<S> clazz);

  List<WebElement> toWebElements();
}
