package ru.otus;

import ru.otus.collections.impl.DIYarrayList;

import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d"};
        List<String> list = new DIYarrayList<>();
        Collections.addAll(list, array);
        list.add("e");
    }
}
