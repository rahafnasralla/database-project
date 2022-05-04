package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    ObservableList<String> updatelist = FXCollections.observableArrayList("email","address","phone number","martial status","photo","number of unemployed in family","birthdate");
    private Connection con;
    private final  Base main = new Base();
    private user user = userholder.getInstance().getUser();
    @FXML
    private Label name;
    @FXML
    private ImageView img;
    @FXML
    private Label email;
    @FXML
    private Label msg;
    @FXML
    private ComboBox<String> update = new ComboBox<String>();
    @FXML
    private Label address;
    @FXML
    private TextField text;
    @FXML
    private DatePicker date;
    @FXML
    private Button button;
    @FXML
    public void date() {

    }
    @FXML
    public void chooseWhat() {
//        if (update.getValue().equals("birthdate")) {
//            date.setVisible(true);
//            button.setVisible(false);
//            text.setVisible(false);
//
//        }
//        else if (update.getValue().equals("photo")) {
//            button.setVisible(true);
//            date.setVisible(false);
//            text.setVisible(false);
//        }
//        else
//        {
//            button.setVisible(false);
//            date.setVisible(false);
//            text.setVisible(true);
//        }
    }
    @FXML
    public void signout() {
        main.changeScene("login.fxml");

    }
    @FXML
    public void choosePhoto() {

    }
    @FXML
    public void updateInfo() {

    }
    public void initialize(){
        loadData();
//        button.setVisible(false);
//        date.setVisible(false);
//        text.setVisible(false);
    }
    public void loadData(){
        name.setText(user.getFname()+" "+user.getLname());
        email.setText(user.getEmail());
        address.setText(user.get$address());
        InputStream ins = new ByteArrayInputStream(user.getPhoto());
        Image photo = new Image(ins);
        if(photo != null) {
            img.setImage(photo);
            img.setFitWidth(181);
            img.setFitHeight(149);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update.setItems(updatelist);
        loadData();

    }

}
