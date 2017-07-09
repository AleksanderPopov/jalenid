package com.jelenide;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementsConditions {
  public static JelementsCondition visible() {
    return new JelementsCondition() {
      @Override
      public Jelements apply(Jelements jelements) {
        Jelements result = new Jelements()
        try {
          return jelement.isDisplayed() ? jelement : null;
        } catch (Exception e) {
          return null;
        }
      }
    };
  }
}
