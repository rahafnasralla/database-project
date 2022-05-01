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
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class signupController implements Initializable {
    ObservableList<String> asalist = FXCollections.observableArrayList("member","teacher");
    ObservableList<String> bloodlist = FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
    private byte [] photo;
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
    private ComboBox<String> asa = new ComboBox<String>();
    @FXML
    private DatePicker birthdate;

    private String selected;
    @FXML
    private TextField telephone;
    @FXML
    private TextField disabled;
    @FXML
    private TextField nofamily;
    @FXML
    private TextField nounemployed;
    @FXML
    private TextField nodisabled;
    @FXML
    private TextField income;
    @FXML
    private ToggleGroup mstatus;
    @FXML
    private RadioButton married;
    @FXML
    private RadioButton single;
    @FXML
    private RadioButton divorced;
    @FXML
    private ComboBox<String> bloodtype = new ComboBox<String>();


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
        if(selected.equals("memeber"))
        {
            main.changeScene("member.fxml");
        }
        else if (selected=="teacher")
        {
            //insert
            //popup then change scene
        }


    }
    @FXML
    public void chooseblood(){
        user.setBlood(bloodtype.getValue());
    }
    @FXML
    public void addPhoto(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if(file != null)
            try {
                InputStream fin = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];

                for (int readNum; (readNum = fin.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                    photo = bos.toByteArray();

            } catch (IOException ex) {
                Logger.getLogger("ss");
            }
    }
    @FXML
    public void choosemembership(){
         //popup pops up
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asa.setItems(asalist);
        bloodtype.setItems(bloodlist);

    }
}
