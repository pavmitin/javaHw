package ru.otus;

import org.hamcrest.MatcherAssert;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.collections.impl.DIYarrayList;

import static org.hamcrest.core.IsEqual.equalTo;

public class TestClass {

    @Before
    public void before() {
        System.out.println("Starting before test method...");
    }

    @Test
    public void testMethod1() {
        System.out.println("Starting first testmethod...");
        DIYarrayList<String> list = new DIYarrayList();
        list.add("test1");
        list.add("test2");
        int actual = 1;
        int expected = list.size();
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testMethod2() {
        System.out.println("Starting second testmethod...");
        DIYarrayList<String> list = new DIYarrayList();
        list.add("test1");
        list.add("test2");
        String expected = list.remove(1);
        String actual = "test2";
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testMethod3() {
        System.out.println("Starting third method...");
        DIYarrayList<String> list = new DIYarrayList();
        list.add("test1");
        list.add("test2");
        String actual = "test2";
        String expected = list.get(0);
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @After
    public void after() {
        System.out.println("Starting after test method...");
    }

}
