package main.project;

import java.time.LocalDate;
public class user {
    private int ID;
    private int pass;
    private String fname;
    private String lname;
    private int pnumber;
    private String $address;
    private String e_mail;
    private String gender;
    private LocalDate birthdate;
    private int mngflag;
    private int elect;
    private int votes;
    private String blood;
    private String disable;
    private String status;
    private byte [] photo;
    public user()
    {
      ID=0;
      pass=elect=0;
      pnumber=mngflag=votes=0;
      fname=lname=$address=e_mail=gender=null;
      blood="A+";
      disable="  ";
      status = "single";
      birthdate=LocalDate.of(2013, 6, 13);
    }
    public void setID(int ID) {this.ID=ID;}
    public int getID() {return this.ID;}
    public void setPass(int pass) {this.pass=pass;}
    public int getPass() {return this.pass;}
    public void setPnumber(int pnumber) {this.pnumber=pnumber;}
    public int getPnumber() {return this.pnumber;}
    public void setFname(String fname) {this.fname=fname;}
    public String getFname() {return this.fname;}
    public void setLname(String lname) {this.lname=lname;}
    public String getLname() {return this.lname;}
    public void set$address(String address) {this.$address=address;}
    public String get$address() {return this.$address;}
    public void setEmail(String email) {this.e_mail=email;}
    public String getEmail() {return this.e_mail;}
    public void setGender(String gender) {this.gender=gender;}
    public String getGender() {return this.gender;}
    public void setBirthdate(LocalDate birthdate) {this.birthdate=birthdate;}
    public LocalDate getBirthdate() {return this.birthdate;}

    public void setBlood(String blood) {this.blood=blood;}
    public String getBlood() {return this.blood;}
    public void setDisable(String disable) {this.disable=disable;}
    public String getDisable() {return this.disable;}
    public void setStatus(String status) {this.status=status;}
    public String getStatus() {return this.status;}

    public void setElect(int elect) {this.elect=elect;}
    public int getElect() {return this.elect;}
    public void setMngflag(int mngflag) {this.mngflag=mngflag;}
    public int getMngflag() {return this.mngflag;}
    public void setVotes(int votes) {this.votes=votes;}
    public int getVotes() {return this.votes;}
    public void setPhoto(byte[] photo) {this.photo=photo;}
    public byte[] getPhoto() {return this.photo;}


}
