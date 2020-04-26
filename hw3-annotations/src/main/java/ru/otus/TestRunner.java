package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.List;

import static java.lang.String.format;

public class TestRunner {

    private Class clazz;
    private static int successTestCount = 0;
    private static int failTestCount = 0;
    private static int testCount = 0;

    public TestRunner(Class clazz) {
        this.clazz = clazz;
        run();
    }

    public TestRunner(String className) {
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(format("Class with name %s doesn't exist!", className));
        }
        run();
    }

    private void run() {
        List<Method> beforeMethods = ReflectionHelper.getMethodsByAnnotation(clazz, Before.class);
        List<Method> testMethods = ReflectionHelper.getMethodsByAnnotation(clazz, Test.class);
        testCount = testMethods.size();
        List<Method> afterMethods = ReflectionHelper.getMethodsByAnnotation(clazz, After.class);
        System.out.println("Testing class: " + clazz.getName());
        for (Method method : testMethods) {
            Object testObject = ReflectionHelper.instantiate(clazz);
            callMethods(testObject, beforeMethods);
            try {
                ReflectionHelper.callMethod(testObject, method);
                successTestCount++;
            } catch (Exception e) {
                e.printStackTrace();
                failTestCount++;
            }
            callMethods(testObject, afterMethods);
        }
        System.out.println("----------- TEST STATISTICS -----------");
        printTestStatistics();
    }

    private void callMethods(Object object, List<Method> methods) {
        if (!methods.isEmpty()) {
            for (Method m : methods) {
                try {
                    ReflectionHelper.callMethod(object, m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printTestStatistics() {
        System.out.println(
                format(
                        "All tests: %s ; Success Tests: %s ; Failed Tests: %s ",
                        testCount,
                        successTestCount,
                        failTestCount
                )
        );
    }
}
