package com.jelenide.conditions;

import com.jelenide.elements.Jelements;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementsConditions {

  public static JelementsCondition size(int size) {
    return new JelementsCondition() {
      @Override
      public Jelements apply(Jelements jelements) {
        try {
          return jelements.size() == size ? jelements : null;
        } catch (Exception e) {
          return null;
        }
      }
    };
  }

  public static JelementsCondition sizeGreaterThan(int size) {
    return new JelementsCondition() {
      @Override
      public Jelements apply(Jelements jelements) {
        try {
          return jelements.size() >= size ? jelements : null;
        } catch (Exception e) {
          return null;
        }
      }
    };
  }

}
