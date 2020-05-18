package ru.otus.gc;

import ru.otus.gc.bench.Benchmark;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Runner {

    private static Map<String, GCStatistics> statisticsMap = new TreeMap();
    private static List<GarbageCollectorMXBean> gcbeans;


    public static void main(String... args) throws Exception {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        int elementsCount = 30000000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);
        mbean.setElementsCount(elementsCount);
        gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        try {
            mbean.run();
        } catch (OutOfMemoryError e) {
            System.out.println(e.getLocalizedMessage());
        }
        switchOnMonitoring();
    }

    private static void switchOnMonitoring() {
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            if (statisticsMap.containsKey(gcbean.getName())) {
                GCStatistics gcStatistics = statisticsMap.get(gcbean.getName());
                gcStatistics.setGcCount(gcbean.getCollectionCount());
                gcStatistics.setCollectionTime(gcbean.getCollectionTime());
            } else {
                statisticsMap.put(
                        gcbean.getName(),
                        new GCStatistics(
                                gcbean.getCollectionCount(),
                                gcbean.getCollectionTime()
                        )
                );
            }
        }
        statisticsMap.forEach((key, value) -> value.printStatistics(key));
    }
}


