package com.jelenide;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class ReflectionTools {

  public static <T extends Jelement> T newInstanceWithFieldValue(Class<T> type, String fieldName, Object fieldValue) {

    try {
      T instance = type.newInstance();
      Class clazz = type;

      Class classWithField = findClassWithField(clazz, fieldName);

      setFieldValue(classWithField, instance, fieldName, fieldValue);

      return instance;
    } catch (IllegalAccessException | InstantiationException e) {
      throw new AssertionError(e);
    }
  }

  public static void setFieldValue(Class clazz, Object instance, String fieldName, Object fieldValue) {
    try {
      Field field = findClassWithField(clazz, fieldName).getDeclaredField(fieldName);
      field.setAccessible(true);

      Field modifiers = field.getClass().getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);


      field.set(instance, fieldValue);
    } catch (NoSuchFieldException | IllegalAccessException e) {
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
