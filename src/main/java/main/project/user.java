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
    public user()
    {
      ID=0;
      pass=0;
      pnumber=0;
      fname=lname=$address=e_mail=gender=null;
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


}
