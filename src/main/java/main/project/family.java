package main.project;

public class family {
    private int nomember;
    private int nodisable;
    private int nounemployed;
    private int mincome;
    private int telephone;


    public family() {
        nodisable=nomember=nounemployed=telephone=mincome=0;
    }
    public void setNomember(int nomember){this.nomember=nomember;}
    public int getNomember(){return this.nomember;}
    public void setNounemployed(int nounemployed){this.nounemployed=nounemployed;}
    public int getNounemployed(){return this.nounemployed;}
    public void setNodisable(int nodisable){this.nodisable=nodisable;}
    public int getNodisable(){return this.nodisable;}
    public void setMincome(int mincome){this.mincome=mincome;}
    public int getMincome(){return this.mincome;}
    public void setTelephone(int telephone){this.telephone=telephone;}
    public int getTelephone(){return this.telephone;}
}
