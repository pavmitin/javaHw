package ru.otus;

public class Main {
    public static void main(String[] args) {
        TestLogging testLogging = Ioc.createLogger();
        testLogging.calculation(1);
        testLogging.calculation(1,1);
        testLogging.calculation(1.0,1);
        testLogging.calculation();
        testLogging.test1("Hello!", 1);
    }
}
