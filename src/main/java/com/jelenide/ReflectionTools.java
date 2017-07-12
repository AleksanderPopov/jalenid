package com.jelenide;

import java.lang.reflect.Field;

/**
 * Created by apop on 7/12/2017.
 */
class ReflectionTools {

  public static void setFieldValue(Object object, String fieldname, Object fieldValue) {
    try {
      Class<?> clazz = getClassWithField(object.getClass(), fieldname);
      Field field = clazz.getDeclaredField(fieldname);
      field.setAccessible(true);
      field.set(object, fieldValue);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new AssertionError();
    }
  }

  private static Class<?> getClassWithField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
    while (true) {
      if (clazz.getDeclaredField(fieldName) != null) {
        return clazz;
      } else if (clazz.getSuperclass().equals(Object.class)) {
        throw new AssertionError("No field '" + fieldName + "' found.");
      } else {
        clazz = clazz.getSuperclass();
      }
    }
  }

}
