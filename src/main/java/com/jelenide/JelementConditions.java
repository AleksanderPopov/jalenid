package com.jelenide;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementConditions {

  public static JelementCondition visible() {
    return new JelementCondition() {
      @Override
      public Jelement apply(Jelement jelement) {
        try {
          return jelement.isDisplayed() ? jelement : null;
        } catch (Exception e) {
          return null;
        }
      }
    };
  }
}
