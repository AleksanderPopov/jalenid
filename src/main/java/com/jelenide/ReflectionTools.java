package com.jelenide;

import java.lang.reflect.Field;

/**
 * Created by apop on 7/12/2017.
 */
class ReflectionTools {

  public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();

    try {
      while (true) {
        try {
          if (clazz.getDeclaredField(fieldName) != null) {
            break;
          }
        } catch (NoSuchFieldException e) {
          if (clazz.getSuperclass().equals(Object.class)) {
            throw new AssertionError("No field '" + fieldName + "' found.");
          } else {
            clazz = clazz.getSuperclass();
          }
        }
      }

      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(object, fieldValue);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      throw new AssertionError();
    }
  }

  private static Class<?> getClassWithField(Class<?> clazz, String fieldName) {
    while (true) {
      try {
        if (clazz.getDeclaredField(fieldName) != null) {
          return clazz;
        }
      } catch (NoSuchFieldException e) {
        if (clazz.getSuperclass().equals(Object.class)) {
          throw new AssertionError("No field '" + fieldName + "' found.");
        } else {
          clazz = clazz.getSuperclass();
        }
      }
    }
  }

}
