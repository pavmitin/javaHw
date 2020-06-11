package ru.otus;

import ru.otus.annotations.Log;

public class TestLoggingImpl implements TestLogging {
    @Log
    @Override
    public void calculation(int param) {

    }

    @Log
    @Override
    public void calculation(int param1, int param2) {

    }

    @Log
    @Override
    public void calculation(int param1, int param2, int param3) {

    }

    @Override
    public void calculation(double param1, int param2) {

    }

    @Log
    @Override
    public void calculation() {

    }

    @Log
    @Override
    public void test1(String param1, Integer param2) {

    }
}
