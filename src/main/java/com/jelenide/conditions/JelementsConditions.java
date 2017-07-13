package com.jelenide.conditions;

import com.jelenide.Jelement;
import com.jelenide.Jelements;

/**
 * Created by Alex on 7/9/2017.
 */
public class JelementsConditions {

  public static <T extends Jelement> JelementsCondition<T> size(int size) {
    return new JelementsCondition<T>() {
      @Override
      public Jelements<T> apply(Jelements<T> jelements) {
        try {
          return jelements.size() == size ? jelements : null;
        } catch (Exception e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return "$classname{}";
      }
    };
  }

  public static <T extends Jelement> JelementsCondition<T> sizeGreaterThan(int size) {
    return new JelementsCondition<T>() {
      @Override
      public Jelements<T> apply(Jelements<T> jelements) {
        try {
          return jelements.size() >= size ? jelements : null;
        } catch (Exception e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return "$classname{}";
      }
    };
  }

}
