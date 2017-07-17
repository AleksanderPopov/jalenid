package com.jelenide.v2;

import com.jelenide.v2.finders.Finder;
import com.jelenide.v2.jelements.Jelement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ReflectionTools {

  public static <T extends Jelement, F extends Finder> T newInstance(Class<T> jelementClass, Class<F> finderCLass, Object... constructorParameters) {
    try {
      return jelementClass.getConstructor(finderCLass).newInstance(constructorParameters);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return null;
  }

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
