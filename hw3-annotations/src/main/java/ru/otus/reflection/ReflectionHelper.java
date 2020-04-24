package ru.otus.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static void callMethod(Object object, Method method) throws InvocationTargetException {
        try {
            method.setAccessible(true);
            method.invoke(object);
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
        } finally {
            if (method != null && !method.isAccessible()) {
                method.setAccessible(false);
            }
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static <T> List<Method> getMethodsByAnnotation(Class<T> type, Class<? extends Annotation> annotationClass) {
        Method[] allMethods = type.getMethods();
        return Arrays.stream(allMethods)
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }


}
