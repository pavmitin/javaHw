package ru.otus;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.objects.Company;
import ru.otus.objects.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGsonTest {

    private Gson gson;
    private MyGson myGson;

    @BeforeEach
    void setUp() {
        gson = new Gson();
        myGson = new MyGson();
    }

    @Test
    void testSerializePersonObject() throws IllegalAccessException {
        Person person = new Person(55, "Mike", "Tyson");
        String expected = gson.toJson(person);
        String actual = myGson.toJson(person);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeArrayOfChar() throws IllegalAccessException {
        char[] symbols = {'a', 'x', '\u1223'};
        String expected = gson.toJson(symbols);
        String actual = myGson.toJson(symbols);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeCollection() throws IllegalAccessException {
        List<String> persons = Arrays.asList("Mike", "John");
        String expected = gson.toJson(persons);
        String actual = myGson.toJson(persons);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeArrayOfInteger() throws IllegalAccessException {
        int[] departmentIds = new int[]{1, 2, 3};
        String expected = gson.toJson(departmentIds);
        String actual = myGson.toJson(departmentIds);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeMixed() throws IllegalAccessException {
        List<String> persons = Arrays.asList("Mike", "John");
        Integer[] departmentIds = new Integer[]{1, 2, 3};
        Company company = new Company(1, "Sberbank", "Moscow", persons, departmentIds);
        String expected = gson.toJson(company);
        String actual = myGson.toJson(company);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeNull() throws IllegalAccessException {
        String expected = gson.toJson(null);
        String actual = myGson.toJson(null);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeFloat() throws IllegalAccessException {
        String expected = gson.toJson(1.1f);
        String actual = myGson.toJson(1.1f);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeChar() throws IllegalAccessException {
        String expected = gson.toJson('a');
        String actual = myGson.toJson('a');
        assertEquals(expected, actual);
    }

    @Test
    void testSerializePersonWithNullParam() throws IllegalAccessException {
        Person person = new Person(55, null, "Tyson");
        String expected = gson.toJson(person);
        String actual = myGson.toJson(person);
        assertEquals(expected, actual);
    }

    @Test
    void testSerializeBoolean() throws IllegalAccessException {
        String expected = gson.toJson(false);
        String actual = myGson.toJson(false);
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
    }
}