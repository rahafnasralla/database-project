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
import java.util.ResourceBundle;
import java.util.logging.Logger;

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
        if (update.getValue().equals("birthdate")) {
            date.setVisible(true);
            button.setVisible(false);
            text.setVisible(false);

        }
        else if (update.getValue().equals("photo")) {
            button.setVisible(true);
            date.setVisible(false);
            text.setVisible(false);
        }
        else
        {
            button.setVisible(false);
            date.setVisible(false);
            text.setVisible(true);
        }
    }
    @FXML
    public void signout() {
        userholder.getInstance().setUser(null);
        main.changeScene("choose.fxml");

    }
    @FXML
    public void choosePhoto() {

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
                user.setPhoto(bos.toByteArray());


            } catch (IOException ex) {
                Logger.getLogger("ss");
            }
    }
    @FXML
    public void updateInfo() {

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
        update.setItems(updatelist);
        loadData();
        button.setVisible(false);
        date.setVisible(false);
        text.setVisible(false);

    }

}
