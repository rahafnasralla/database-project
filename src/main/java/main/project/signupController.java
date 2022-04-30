package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class signupController implements Initializable {
    ObservableList<String> asalist = FXCollections.observableArrayList("member","teacher");
    private Connection con;
    private final  Base main = new Base();
    static user user=new user();
    @FXML
    private TextField idnumber;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private ToggleGroup sex;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm;
    @FXML
    private ComboBox<String> asa;
    @FXML
    private DatePicker birthdate;
    private String selected;



    @FXML
    public void getDate() {
      user.setBirthdate(birthdate.getValue());

    }

    @FXML
    public void chooseasa() {
        selected=asa.getValue();

    }
    @FXML
    public void signingup() {
        if(idnumber.getText().isEmpty()||firstname.getText().isEmpty()||lastname.getText().isEmpty()||phonenumber.getText().isEmpty()||address.getText().isEmpty()||email.getText().isEmpty()||
        sex.getSelectedToggle()==null||password.getText().isEmpty()||confirm.getText().isEmpty()||selected==null)
        {
            //msg
        }
        //right format for integers
        user.setID(Integer.parseInt(idnumber.getText()));
        user.setFname(firstname.getText());
        user.setLname(lastname.getText());
        user.setPnumber(Integer.parseInt(phonenumber.getText()));
        user.set$address(address.getText());
        user.setEmail(email.getText());
        if(female.isSelected())
            user.setGender("female");
        else if (male.isSelected())
            user.setGender("male");
        user.setPass(Integer.parseInt(password.getText()));
        if(user.getPass()==Integer.parseInt(confirm.getText()))
        {
            ///msg correct
        }
        else
        {
            ///add msg
        }
        if(selected=="memeber")
        {
            main.changeScene("member.fxml");
        }
        else if (selected=="teacher")
        {
            //popup then change scene
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asa.setItems(asalist);

    }
}
