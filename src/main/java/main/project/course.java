package main.project;

import java.time.LocalDate;

public class course {
    private int COURSE_ID;
    private String SPORT;
    private int COURSE_DURATION;
    private int NO_ENROLLED;
    private LocalDate START_DATE;
    private int PRICE;
    private String TEACHER;

    public course()
    {
        COURSE_ID=PRICE=NO_ENROLLED=COURSE_DURATION=0;
        START_DATE=LocalDate.now();
        SPORT=TEACHER="";
    }
    public String getTEACHER() {
        return TEACHER;
    }

    public void setTEACHER(String teacher) {this.TEACHER = teacher;}

    public int getPRICE() {
        return PRICE;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }

    public LocalDate getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(LocalDate START_DATE) {
        this.START_DATE = START_DATE;
    }

    public int getNO_ENROLLED() {
        return NO_ENROLLED;
    }

    public void setNO_ENROLLED(int NO_ENROLLED) {
        this.NO_ENROLLED = NO_ENROLLED;
    }

    public int getCOURSE_DURATION() {
        return COURSE_DURATION;
    }

    public void setCOURSE_DURATION(int COURSE_DURATION) {
        this.COURSE_DURATION = COURSE_DURATION;
    }

    public String getSPORT() {
        return SPORT;
    }

    public void setSPORT(String SPORT) {
        this.SPORT = SPORT;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }
}
