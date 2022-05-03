package main.project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base extends Application {
    private static Stage stage;
    private static Stage stage2;
    private static Connection con;
    music Music = new music();
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Base.class.getResource("choose.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 986, 731);
        stage.setTitle("Al'Awda Sports Club");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(scene);
        Music.playMusic("C:\\Users\\rova\\Desktop\\sound.wav");
        stage.show();
    }
    public void changeScene(String fxml) {
        try {

            if(!fxml.isEmpty()) {
                Parent root = FXMLLoader.load(getClass().getResource(fxml));
                this.stage.getScene().setRoot(root);
                this.stage.sizeToScene();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void popupScene(String fxml)  {
        try {
            Parent m = FXMLLoader.load(getClass().getResource(fxml));

            Scene ms = new Scene(m);
            ms.getStylesheets().add("CSS.css");
            stage2 = new Stage();
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(ms);
            stage2.show();
        }
        catch (IOException ex) {

        }
    }
    public void closePopup()  {
            stage2.hide();
    }

    public Connection getConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##rova", "123456");
        if(con != null){
            System.out.println("connected successfully!");
        }
        return con;
    }


    public static void main (String[] args) throws SQLException {
        launch();
    }
}
