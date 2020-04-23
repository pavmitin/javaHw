package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.util.logging.Logger;

public class TestClass {
    private static Logger logger = Logger.getLogger(TestClass.class.getName());

    @Before
    public void before() {
        logger.info("Starting before test method...");
    }

    @Test
    public void testMethod1() {
        logger.info("Starting first method...");
    }

    @Test
    public void testMethod2() {
        logger.info("Starting second method...");
    }

    @Test
    public void testMethod3() {
        logger.info("Starting third method...");
    }

    @After
    public void after() {
        logger.info("Starting after test method...");
    }

}
