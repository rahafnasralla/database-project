package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class profileController implements Initializable {
    private final  Base main = new Base();
    private user user = userholder.getInstance().getUser();
    @FXML
    private Label votes;
    @FXML
    private Label name;
    @FXML
    private ImageView img;
    @FXML
    private Label email;
    @FXML
    private ComboBox<String> update = new ComboBox<String>();
    @FXML
    private Label address;


    @FXML
    public void signout() {
        userholder.getInstance().setUser(null);
        main.changeScene("choose.fxml");

    }


    public void loadData(){
        name.setText("Name : "+user.getFname()+" "+user.getLname());
        email.setText("Email : "+user.getEmail());
        address.setText("Address : "+user.get$address());
        InputStream ins = new ByteArrayInputStream(user.getPhoto());
        Image photo = new Image(ins);
        if(photo != null) {
            img.setImage(photo);
            img.setFitWidth(149);
            img.setFitHeight(149);
            img.setStyle("-fx-border-radius: 50");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        votes.setVisible(false);
        if(user.getElect()!=1)
        {   votes.setVisible(false);
            main.popupScene("nominee.fxml");
            user.setElect(1);}
        else if(user.getElect()==1) {
            votes.setText("number of votes : " + user.getVotes());
            votes.setVisible(true);}
    }
}
