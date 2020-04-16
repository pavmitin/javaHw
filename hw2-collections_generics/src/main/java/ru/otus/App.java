package ru.otus;

import org.checkerframework.checker.units.qual.A;
import ru.otus.collections.impl.DIYarrayList;

import java.util.*;

public class App {
    public static void main(String[] args) {
        String[] citiesToVisit = {
                "Prague",
                "San Francisco",
                "Kiev",
                "Milan",
                "Minsk"
        };
        DIYarrayList<String> visitedCities = new DIYarrayList<>();
        visitedCities.add("Moscow");
        visitedCities.add("Paris");
        visitedCities.add("London");
        visitedCities.add("Budapest");
        visitedCities.add("Los Angeles");
        visitedCities.add("Tiraspol");
        visitedCities.add("Tokyo");
        visitedCities.add("Toronto");
        visitedCities.add("Toulouse");
        visitedCities.add("Valencia");
        visitedCities.add("Varna");
        visitedCities.add("Washington");
        visitedCities.add("Vienna");
        visitedCities.add("New York");
        visitedCities.add("Vitebsk");
        visitedCities.add("Jakarta");
        visitedCities.add("Dnipropetrovsk");
        visitedCities.add("Donetsk");
        visitedCities.add("Dresden");
        visitedCities.add("Dublin");
        visitedCities.add("Douala");
        visitedCities.add("Dushanbe");
        visitedCities.add("Duesseldorf");

        Collections.addAll(visitedCities, citiesToVisit);
        System.out.println("------ List visitedCities after addAll citiesToVisit ------");
        System.out.print(visitedCities);
        System.out.println("\r\n");

        DIYarrayList<String> likedCities = new DIYarrayList<>();
        likedCities.add("Sevastopol");
        likedCities.add("Tver");
        Collections.copy(visitedCities, likedCities);
        System.out.println("------ List visitedCities after copy likedCities ------");
        System.out.print(visitedCities);

        Collections.sort(visitedCities, Comparator.naturalOrder());
        System.out.println("\r\n");
        System.out.println("------ List visitedCities after sort ------");
        System.out.print(visitedCities);

        System.out.println(visitedCities.lastIndexOf("Tver"));
        System.out.println(visitedCities.remove(0));
        System.out.println(visitedCities);
        System.out.println(visitedCities.get(3));
        System.out.println(visitedCities.size());

        visitedCities.add(3, "Sankt Peterburg");
        System.out.println("\r\n");
        System.out.println(visitedCities);
        System.out.println(visitedCities.size());
        System.out.println(visitedCities.set(27, "Detroit"));
        System.out.println(visitedCities);
    }
}
