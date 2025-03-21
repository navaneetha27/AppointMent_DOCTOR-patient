package org.example.entity;

public class Slots {
    String startTime;
    String endTime;
    boolean isBooked;

    public Slots(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = false;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String toString(){
        return this.startTime + "  -- " + this.getEndTime() + (this.isBooked ? "Booked" : "Available");
    }
}
