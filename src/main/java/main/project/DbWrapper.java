package main.project;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

public class DbWrapper {
    private static Connection con;

    private static PreparedStatement stmt;

    private static String sql;

    private static final Base main = new Base();
    public static user getUser(ResultSet r) {
        try {
            byte[] photo = r.getBytes("photo");
            int number = r.getInt("PHONE_NUMBER");
            String address = r.getString("ADDRESS");
            String email = r.getString("EMAIL");
            String pass = r.getString("PASSWORD");
            String status = r.getString("MARTIAL_STATUS");
            String blood = r.getString("BLOOD_TYPE");
            int ID = r.getInt("SSN");
            String gender = r.getString("GENDER");
            String disability = r.getString("DISABILITY");
            LocalDate bdate = r.getDate("BIRTHDATE").toLocalDate();
            String fname = r.getString("F_NAME");
            String lname = r.getString("L_NAME");
            int votes = r.getInt("NOVOTES");
            int elect = r.getInt("ELECTED");
            int flag = r.getInt("MFLAG");

            user user = new user();
            user.setPnumber(number);
            user.set$address(address);
            user.setEmail(email);
            user.setPass(pass);
            user.setStatus(status);
            user.setPhoto(photo);
            user.setBlood(blood);
            user.setID(ID);
            user.setBirthdate(bdate);
            user.setGender(gender);
            user.setDisable(disability);
            user.setElect(elect);
            user.setMngflag(flag);
            user.setFname(fname);
            user.setLname(lname);
            user.setVotes(votes);
            return user;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }
}
