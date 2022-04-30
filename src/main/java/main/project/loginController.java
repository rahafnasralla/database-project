package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.member;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController implements Initializable {

      ObservableList<String> list = FXCollections.observableArrayList("member","administrator","teacher");
      private member user;
      private Connection con;
      private final  Base main = new Base();
      @FXML
      private ComboBox<String> choice;
      @FXML
      private TextField username;
      @FXML
      private TextField password;
      String selected;
      @FXML
      public void tosignup() {
          main.changeScene("$signup.fxml");
      }

      @FXML
      public void loginchoose() {
            selected = choice.getValue();
            if(selected!=null)
            main.changeScene("$login.fxml");
            else
            {
                //show message
            }
      }
    @FXML
    public void login() {
          if(selected=="member")
          {

          }
          else if (selected=="administrator")
          {

          }
          else if(selected=="teacher")
          {

          }

    }
    @FXML
    public void forgot() {
        main.changeScene("$changepassword.fxml");
    }

      private void setUser() {
        userholder.getInstance().setUser(user);
       }

    private boolean validLogin() {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            //msg.setText("Please Enter Information");
        }
        else if(isUser()){
           // msg.setText("Success!");
            return true;
        }
        else {
            //msg.setText("Invalid username or Password");
        }
        return false;
    }

    private boolean isUser(){
        try {
            Connection con = main.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from member";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                if (username.getText().matches(user) && password.getText().matches(pass)) {
                    //this.user = DbWraper.wrapUser(resultSet);
                    stmt.close();
                    con.close();
                    return true;
                }
            }
            stmt.close();
            con.close();
        }catch (Exception e){
            System.out.println("Error with getting and checking Users from DB");
            e.printStackTrace();
        }
        return false;
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            choice.setItems(list);
        }
//
//
}
