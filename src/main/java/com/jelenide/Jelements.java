package com.jelenide;

import com.jelenide.conditions.JelementCondition;
import com.jelenide.conditions.JelementsCondition;
import org.openqa.selenium.WebElement;

import java.util.Collection;

/**
 * Created by Alex on 7/10/2017.
 */
public interface Jelements<T extends Jelement> extends Collection<T> {

  Jelements<T> filter(JelementCondition condition);

  Jelements<T> shouldHave(JelementsCondition condition);

  T first();

  Collection<? extends WebElement> find();

}
