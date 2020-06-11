package ru.otus;

import ru.otus.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Ioc {

    static TestLogging createLogger() {
        InvocationHandler handler = new LoggingInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final TestLogging loggingClass;
        private final Set<Method> logingMethods;

        LoggingInvocationHandler(TestLogging testLogging) {
            this.loggingClass = testLogging;
            this.logingMethods = getMethodsWithLogAnnotation(testLogging.getClass());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logingMethods.stream()
                    .noneMatch(m ->
                            method.getName().equals(m.getName()) &&
                                    method.getParameterCount() == m.getParameterCount() &&
                                    isTypesOfParameterEquals(method, m)
                    )
            ) {
                return method.invoke(loggingClass, args);
            }
            String message = "executed method: " + method.getName() + ", params: ";
            StringBuilder builder = new StringBuilder();
            builder.append(message);
            if (args != null) {
                String params = Arrays.stream(args)
                        .map(String::valueOf)
                        .collect(Collectors.joining("; "));
                builder.append(params);
            }
            System.out.println(builder.toString());
            return method.invoke(loggingClass, args);
        }

        private Set<Method> getMethodsWithLogAnnotation(Class<?> object) {
            return Arrays.stream(object.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class)).collect(Collectors.toSet());
        }

        private boolean isTypesOfParameterEquals(Method method1, Method method2) {
            return Arrays.equals(method1.getParameterTypes(), method2.getParameterTypes());
        }

        @Override
        public String toString() {
            return "LoggingInvocationHandler{" +
                    "loggingClass=" + loggingClass +
                    '}';
        }
    }
}
