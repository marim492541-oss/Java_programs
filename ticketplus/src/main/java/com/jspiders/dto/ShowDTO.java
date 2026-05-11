package com.jspiders.dto;

import java.time.LocalTime;

public class ShowDTO {

    private LocalTime StartTime;
    private LocalTime EndTime;

    public LocalTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalTime startTime) {
        StartTime = startTime;
    }

    public LocalTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalTime endTime) {
        EndTime = endTime;
    }

    @Override
    public String toString() {
        return "ShowDTO{" +
                "StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                '}';
    }
}
