package ru.otus.gc.bench;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {
    private int elementsCount;
    private int loopCounter = 0;

    public void run() throws InterruptedException {
        List<String> list = new ArrayList<>();
        while (true) {
            for (int j = 0; j < elementsCount; j++) {
                list.add(new String(new char[0]));
            }
            for (int j = 0; j < elementsCount / 3000; j++) {
                list.remove(j);
            }
            Thread.sleep(10);
            loopCounter++;
            System.out.println("loopCounter:" + loopCounter);

        }

    }

    @Override
    public int getElementsCount() {
        return elementsCount;
    }

    @Override
    public void setElementsCount(int elementsCount) {
        System.out.println("set elements count:" + elementsCount);
        this.elementsCount = elementsCount;
    }

}
