package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

public class TestRunner {

    private Class clazz;
    private static Logger logger = Logger.getLogger(TestRunner.class.getName());

    public TestRunner(Class clazz) {
        this.clazz = clazz;
    }

    public void run() {
        List<Method> beforeMethods = ReflectionHelper.getMethodsByAnnotation(clazz, Before.class);
        List<Method> testMethods = ReflectionHelper.getMethodsByAnnotation(clazz, Test.class);
        List<Method> afterMethods = ReflectionHelper.getMethodsByAnnotation(clazz, After.class);
        for (Method method : testMethods) {
            Object testObject = ReflectionHelper.instantiate(clazz);
            logger.info("Testing class: " + clazz.getName());
            callMethods(testObject, beforeMethods);
            ReflectionHelper.callMethod(testObject, method.getName());
            callMethods(testObject, afterMethods);
        }
    }

    private void callMethods(Object object, List<Method> methods) {
        if (!methods.isEmpty()) {
            methods
                    .forEach(
                            m -> ReflectionHelper.callMethod(object, m.getName()));
        }
    }
}
