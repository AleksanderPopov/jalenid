package com.jelenide.v2.jelements;

import com.jelenide.v2.conditions.JelementCondition;
import com.jelenide.v2.conditions.JelementsCondition;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Jelements {

  Jelements should(JelementsCondition condition);

  Jelements filter(JelementCondition condition);

  Jelement get(int index);

  Jelement first();

  List<WebElement> toWebElements();
}
