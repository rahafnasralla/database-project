package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.member;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class loginController implements Initializable {

      ObservableList<String> list = FXCollections.observableArrayList("member","administrator","teacher");
      int managerflag;
      private member user;
      private Connection con0;
      private final  Base main = new Base();
      @FXML
      private Label msg;
      @FXML
      private ComboBox<String> choice= new ComboBox<String>();
      @FXML
      private TextField username;
      @FXML
      private TextField userID;
      @FXML
      private PasswordField password;
      @FXML
      private PasswordField reset;
      static String selected;
      @FXML
      public void tosignup() {
          main.changeScene("$signup.fxml");
      }

      @FXML
      public void loginchoose() {
            selected = choice.getValue();
            if(selected!=null)
            main.changeScene("login.fxml");
            else
            {
                //show message
            }
      }
    @FXML
    public void login() {
          System.out.println("hii");
          if(selected.equals("member")||selected.equals("administrator"))
          {
              if(username.getText().isEmpty() || password.getText().isEmpty()){
                  msg.textProperty().unbind();
                  msg.setText("Please Enter Information");
                  return;
              }
              if(isMember()) {
                  if (managerflag == 1)
                      main.changeScene("$signup.fxml"); ///for now
                  else
                      main.changeScene("member_dashboard.fxml");
              }

          }

          else if(selected.equals("teacher"))
          {
              if(username.getText().isEmpty() || password.getText().isEmpty()){
                  msg.textProperty().unbind();
                  msg.setText("Please Enter Information");
                  return;
              }
              if(isTeacher())
                  main.changeScene("member_dashboard.fxml");  //for now
          }

    }
    @FXML
    public void forgot() {
        main.popupScene("changepassword.fxml");
    }

      private void setUser() {
        userholder.getInstance().setUser(user);
       }



    private boolean isMember(){
        try {
            Connection con = main.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from MEMBER";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                msg.setTextFill(Color.valueOf("#698ee4"));
                int name = resultSet.getInt("SSN");
                String pass = resultSet.getString("password");
                 managerflag = resultSet.getInt("mflag");
                if (Integer.parseInt(username.getText())==name && password.getText().matches(pass)) {
                    //user = DbWrapper.getUser(resultSet);
                    stmt.close();
                    con.close();
                    msg.setText("Success");
                    return true;
                }
            }
            stmt.close();
            con.close();
            msg.setTextFill(Color.RED);
            msg.setText("Invalid Username or Password");
            return false;
        }catch (Exception e){
            System.out.println("Error with getting and checking Users from DB");
            e.printStackTrace();
            msg.textProperty().unbind();
            return false;
        }
    }
    private boolean isTeacher(){
        try {
            Connection con = main.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from teacher";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                msg.setTextFill(Color.valueOf("#698ee4"));
                int name = rs.getInt("T_SSN");
                String pass = rs.getString("password");
                if (Integer.parseInt(username.getText())==name && password.getText().matches(pass)) {
                    //user = DbWrapper.getUser(resultSet);
                    stmt.close();
                    con.close();
                    msg.setText("Success");
                    return true;
                }
            }
            stmt.close();
            con.close();
            msg.setTextFill(Color.RED);
            msg.setText("Invalid Username or Password");
            return false;
        }catch (Exception e){
            System.out.println("Error with getting and checking Users from DB");
            e.printStackTrace();
            msg.textProperty().unbind();
            return false;
        }
    }
    @FXML
    public void change(){
          //I have to check if the ssn is valid but later
          if(selected.equals("member")||selected.equals("administrator"))
          {
              String sql = "update member set PASSWORD  = ? where SSN = ?";

              try {
                  con0=main.getConnection();
                  PreparedStatement stmt = con0.prepareStatement( sql);
                  stmt.setString(1,reset.getText());
                  stmt.setInt(2,Integer.parseInt(userID.getText()));
                  stmt.executeUpdate();

                  stmt.close();
                  con0.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          else if (selected.equals("teacher"))
          {
              String sql = "update TEACHER set PASSWORD  = ? where T_SSN = ?";

              try {
                  con0=main.getConnection();
                  PreparedStatement stmt = con0.prepareStatement( sql);
                  stmt.setString(1,reset.getText());
                  stmt.setInt(2,Integer.parseInt(userID.getText()));
                  stmt.executeUpdate();

                  stmt.close();
                  con0.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          ///show message and maybe send email but i'll do later
    }
    @FXML
    public void close(){
        main.closePopup();  ///or maybe make it close لما أكبس على login scene maybe
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            choice.setItems(list);
        }
//
//
}
