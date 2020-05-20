package ru.otus.gc;

import static java.lang.String.format;

public class GCStatistics {
    private long gcCount;
    private long collectionTime;

    public GCStatistics(long gcCount, long time) {
        this.gcCount = gcCount;
        this.collectionTime = time;
    }

    public long getGcCount() {
        return gcCount;
    }

    public void setGcCount(long gcCount) {
        this.gcCount = gcCount;
    }

    public long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(long collectionTime) {
        this.collectionTime = collectionTime;
    }

    public void printStatistics(String gcName) {
        System.out.println(format("GC Name: %s; GC CollectionTime: %s seconds; GC Count: %s", gcName, getCollectionTime() / 1000.0, getGcCount()));
    }


}
