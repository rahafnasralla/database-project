package main.project;

import java.time.LocalDate;

public class membership {
    private int cardnumber;
    private int m_ID;
    private int price;
    private LocalDate starting;
    private LocalDate expiring;
    private String type;

    public membership() {
        m_ID=price=cardnumber=0;
        type="monthly";
        starting=LocalDate.now();
        expiring=LocalDate.now().plusMonths(1);
    }
    public void setCardnumber(int cardnumber){this.cardnumber=cardnumber;}
    public int getCardnumber(){return cardnumber;}
    public void setM_ID(int m_ID){this.m_ID=m_ID;}
    public int getM_ID(){return m_ID;}
    public int getPrice(){if (type.equals("monthly"))
        return 25;
    else
        return 300;}
    public void setType(String type){this.type=type;}
    public String getType(){return type;}
    public LocalDate getExpiring(){if (type.equals("monthly"))
                                       return starting.plusMonths(1);
                                   else
                                       return starting.plusYears(1);}
    public LocalDate getStarting(){return starting;}

}
