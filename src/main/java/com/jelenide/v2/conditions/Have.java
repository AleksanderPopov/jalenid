package com.jelenide.v2.conditions;

import com.jelenide.v2.jelements.Jelement;
import com.jelenide.v2.jelements.Jelements;
import org.openqa.selenium.WebDriverException;

/**
 * Created by Alex on 7/17/2017.
 */
public class Have {

  public static JelementsCondition size(int size) {
    return new JelementsCondition() {
      @Override
      public Jelements apply(Jelements jelements) {
        return jelements.toWebElements().size() == size ? jelements : null;
      }
    };
  }

  public static JelementsCondition sizeGreaterThanOrEqual(int size) {
    return new JelementsCondition() {
      @Override
      public Jelements apply(Jelements jelements) {
        return jelements.toWebElements().size() >= size ? jelements : null;
      }
    };
  }

  public static JelementCondition text(String text) {
    return new JelementCondition() {
      @Override
      public Jelement apply(Jelement jelement) {
        try {
          return jelement.toWebElement().getText().contains(text) ? jelement : null;
        } catch (WebDriverException e) {
          return null;
        }
      }
    };
  }

}
