package main.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.logging.Logger;
///make renew available when the membership is about to end
//show nominate popup when it's nomination time
public class dashboardcontroller {
    @FXML
    private StackPane contentarea;
    public void initialize() {
        navigate("member_home.fxml");
    }
    public void navigate(String f){
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(f));
            contentarea.getChildren().removeAll();
            contentarea.getChildren().setAll(fxml);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void home() {
        navigate("member_home.fxml");
    }
    @FXML
    public void profile() {
        navigate("member_profile.fxml");
    }
    @FXML
    public void course() {
        navigate("membercourse.fxml");
    }
    @FXML
    public void event() {
        navigate("member_event.fxml");
    }
    @FXML
    public void vote() {
        navigate("vote.fxml");
    }
    @FXML
    public void renew() {
        navigate("member_renew.fxml");
    }

}
