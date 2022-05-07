package main.project;

import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    public static List<user> getnominees(){
        List<user> users = new ArrayList<>();
        sql  = "Select * from member WHERE ELECTED=1";
        try {
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            ResultSet r = stmt.executeQuery();
            while (r.next()){
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
                users.add(user);

            }
            con.close();
            stmt.close();
            return users;

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;

    }

    public static List<course> getCourses()
    {
        try {
            List<course> List = new ArrayList<>();
            sql = "select PRICE,START_DATE,COURSE_ID,COURSE_DURATION,SPORT,NO_ENROLLED,FNAME,LNAME from COURSE inner join TEACHER on T_SSN=T_ID";
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                course course = new course();
                course.setCOURSE_ID(rs.getInt("COURSE_ID"));
                course.setCOURSE_DURATION(rs.getInt("COURSE_DURATION"));
                course.setNO_ENROLLED(rs.getInt("NO_ENROLLED"));
                course.setPRICE(rs.getInt("PRICE"));
                course.setSPORT(rs.getString("SPORT"));
                course.setSTART_DATE(rs.getDate("START_DATE").toLocalDate());
                course.setTEACHER(rs.getString("FNAME")+""+rs.getString("LNAME"));
                List.add(course);
            }
            con.close();
            stmt.close();
            return List;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<event> getEvents(user u) {
        try {
            int id=10;
            List<event> List = new ArrayList<>();
            sql = "select EVENT_NUMBER,EVENT_DATE,EVENT_NAME,TIME,INTRESTED from EVENT inner join EVENT_MEMBER on EVENT_NUMBER=EVENT_MEMBER.EVENT AND EVENT_MEMBER.MEMBER=?";
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,u.getID()); //for now
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                event e = new event();
                e.setEVENT_DATE(rs.getDate("EVENT_DATE").toLocalDate());
                e.setEVENT_NAME(rs.getString("EVENT_NAME"));
                e.setINTRESTED(rs.getString("INTRESTED"));
                e.setTIME(rs.getString("TIME"));
                e.setEVENT_NUMBER(rs.getInt("EVENT_NUMBER"));
                List.add(e);
            }
            con.close();
            stmt.close();
            return List;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<course> getTeacherCourses(user u)
    {
        try {
            List<course> List = new ArrayList<>();
            sql = "select PRICE,START_DATE,COURSE_ID,COURSE_DURATION,SPORT,NO_ENROLLED,FNAME,LNAME from COURSE inner join TEACHER on T_SSN=T_ID and T_SSN=?";
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,u.getID());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                course course = new course();
                course.setCOURSE_ID(rs.getInt("COURSE_ID"));
                course.setCOURSE_DURATION(rs.getInt("COURSE_DURATION"));
                course.setNO_ENROLLED(rs.getInt("NO_ENROLLED"));
                course.setPRICE(rs.getInt("PRICE"));
                course.setSPORT(rs.getString("SPORT"));
                course.setSTART_DATE(rs.getDate("START_DATE").toLocalDate());
                course.setTEACHER(rs.getString("FNAME")+""+rs.getString("LNAME"));
                List.add(course);
            }
            con.close();
            stmt.close();
            return List;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static user getTeacher(ResultSet r) {
        try {
            int number = r.getInt("PHONENUMBER");
            String address = r.getString("T_ADDRESS");
            String email = r.getString("T_EMAIL");
            String pass = r.getString("PASSWORD");
            int ID = r.getInt("T_SSN");
            String gender = r.getString("T_GENDER");
            LocalDate bdate = r.getDate("T_BIRTHDATE").toLocalDate();
            String fname = r.getString("FNAME");
            String lname = r.getString("LNAME");
            user user = new user();
            user.setPnumber(number);
            user.set$address(address);
            user.setEmail(email);
            user.setPass(pass);
            user.setID(ID);
            user.setBirthdate(bdate);
            user.setGender(gender);
            user.setFname(fname);
            user.setLname(lname);
            return user;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    public static List<event> getAllEvents() {
        try {
            int id=10;
            List<event> List = new ArrayList<>();
            sql = "select * from EVENT ";
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                event e = new event();
                e.setEVENT_DATE(rs.getDate("EVENT_DATE").toLocalDate());
                e.setEVENT_NAME(rs.getString("EVENT_NAME"));
                e.setTIME(rs.getString("TIME"));
                e.setEVENT_NUMBER(rs.getInt("EVENT_NUMBER"));
                List.add(e);
            }
            con.close();
            stmt.close();
            return List;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<user> getusers(){
        List<user> users = new ArrayList<>();
        sql  = "Select * from member WHERE ELECTED=1";
        try {
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            ResultSet r = stmt.executeQuery();
            while (r.next()){
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
                users.add(user);

            }
            con.close();
            stmt.close();
            return users;

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;

    }

    public static void forEvents(List<event> l) {
        List<user> users = getusers();
        List<event> events = getAllEvents();
        for (event e : events) {
            for (user u : users) {
                try {
                    String sql = "INSERT INTO EVENT_MEMBER (EVENT, MEMBER)" + "values(?,?)";
                    con = main.getConnection();
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(2, u.getID());
                    stmt.setInt(1,e.getEVENT_NUMBER() );
                    stmt.executeUpdate();
                    con.close();
                    stmt.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }


}
