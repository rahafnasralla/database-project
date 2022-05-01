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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController implements Initializable {

      ObservableList<String> list = FXCollections.observableArrayList("member","administrator","teacher");
      int managerflag;
      private member user;
      private final  Base main = new Base();
      @FXML
      private Label msg;
      @FXML
      private ComboBox<String> choice= new ComboBox<String>();
      @FXML
      private TextField username;
      @FXML
      private PasswordField password;
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
        main.changeScene("$changepassword.fxml");
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
                String name = resultSet.getString("SSN");
                String pass = resultSet.getString("password");
                 managerflag = resultSet.getInt("mflag");
                if (username.getText().matches(name) && password.getText().matches(pass)) {
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
                String name = rs.getString("T_SSN");
                String pass = rs.getString("password");
                if (username.getText().matches(name) && password.getText().matches(pass)) {
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
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            choice.setItems(list);
        }
//
//
}
