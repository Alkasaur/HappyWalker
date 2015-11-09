package com.alkasaur.happywalker.model;

/**
 * TODO: add class header comment!
 */
public class Entry {
    private long time;
    private long distanceM;
    private EntryStatus status;

    public Entry(){
        status = EntryStatus.none;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDistanceM() {
        return distanceM;
    }

    public void setDistanceM(long distanceM) {
        this.distanceM = distanceM;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }
}
