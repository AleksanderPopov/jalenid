package com.jelenide;

import java.lang.reflect.Field;

/**
 * Created by apop on 7/12/2017.
 */
class ReflectionTools {

  public static <T extends Jelement> T newInstanceWithFieldValue(Class<T> type, String fieldName, Object fieldValue) {

    try {
      T instance = type.newInstance();
      Class clazz = type;

      Class classWithField = findClassWithField(clazz, fieldName);

      Field field = classWithField.getDeclaredField(fieldName);
      field.setAccessible(true);

      field.set(instance, fieldValue);

      return instance;
    } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
      throw new AssertionError(e);
    }
  }


  private static Class findClassWithField(Class clazz, String fieldName) {
    while (true) {
      try {
        clazz.getDeclaredField(fieldName);
        return clazz;
      } catch (NoSuchFieldException e) {
        if (clazz.equals(Object.class))
          throw new AssertionError(e);
        clazz = clazz.getSuperclass();
      }
    }
  }
}
