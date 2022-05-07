package main.project;

import oracle.sql.TIMESTAMP;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class event {
    private int EVENT_NUMBER;
    private LocalDate EVENT_DATE;
    private String EVENT_NAME;
    private String TIME;
    private String INTRESTED;

    public event(){
        EVENT_NUMBER=0;
        INTRESTED=EVENT_NAME="";
       // TIME = LocalTime.now();
        EVENT_DATE = LocalDate.now();
    }

    public String getINTRESTED() {
        return INTRESTED;
    }

    public void setINTRESTED(String INTRESTED) {
        this.INTRESTED = INTRESTED;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    public LocalDate getEVENT_DATE() {
        return EVENT_DATE;
    }

    public void setEVENT_DATE(LocalDate EVENT_DATE) {
        this.EVENT_DATE = EVENT_DATE;
    }

    public int getEVENT_NUMBER() {
        return EVENT_NUMBER;
    }

    public void setEVENT_NUMBER(int EVENT_NUMBER) {
        this.EVENT_NUMBER = EVENT_NUMBER;
    }
}
