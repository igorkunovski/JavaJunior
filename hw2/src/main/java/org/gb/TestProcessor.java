package org.gb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestProcessor {

  /**
   * Для запуска создается тестовый объект с помощью конструктора без аргументов.
   * <p>
   * Данный метод находит все void методы без аргументов в классе, и запускает их.
   */
  public static void runTest(Class<?> testClass) {
    final Constructor<?> declaredConstructor;
    try {
      declaredConstructor = testClass.getDeclaredConstructor(); //создание конструктора без аргументов
    } catch (NoSuchMethodException e) {
      throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
    }

    final Object testObj;
    try {
      testObj = declaredConstructor.newInstance();  // создание объекта с помощью найденного ранее конструктора
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
    }

    List<Method> methods = new ArrayList<>();

    for (Method method : testClass.getDeclaredMethods()) { //проход по списку всех декларированных методов (тестов)
      if (method.isAnnotationPresent(Test.class)) { //проверка аннотации @Test у метода
        checkTestMethod(method);
        methods.add(method);
      }

      methods = methods.stream()
              .filter(m -> !m.isAnnotationPresent(Skip.class)) // отфильтровать методы @Test с аннотацией @Skip
              .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).order())) // sort by order
              .collect(Collectors.toList());
    }

    for (Method method : testClass.getDeclaredMethods()) { //проход по списку всех декларированных методов (тестов)
      if (method.isAnnotationPresent(BeforeEach.class)) { //проверка аннотации @beforeEach у метода
        checkTestMethod(method);
        methods.add(0, method); //добавление в начало коллекции
      }
    }

    for (Method method : testClass.getDeclaredMethods()) { //проход по списку всех декларированных методов (тестов)
      if (method.isAnnotationPresent(AfterEach.class)) { //проверка аннотации @AfterEach у метода
        checkTestMethod(method);
        methods.add(method); // добавление в конец коллекции
      }
    }

    methods.forEach(it -> runTest(it, testObj));
  }

  /**
   * проверка условий подходящего метода
   * @param method - проверяемый метод
   */
  private static void checkTestMethod(Method method) {
    if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
      throw new IllegalArgumentException("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
    }
  }

  /**
   * запуск метода через рефлексию
   * @param testMethod - метод
   * @param testObj - объект, содержащий метод
   */
  private static void runTest(Method testMethod, Object testObj) {
    try {
      testMethod.invoke(testObj);
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
    } catch (AssertionError e) {

    }
  }
}
