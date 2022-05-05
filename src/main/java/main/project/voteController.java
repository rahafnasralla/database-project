package main.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class voteController {
    private Parent root;
    private Connection con;
    private final Base main = new Base();
    private user chosen;
    private List<user> nominees = new ArrayList<>();
    @FXML
    private Label msg;
    @FXML
    private GridPane grid=new GridPane();
    @FXML
    private void initialize(){
        nominees.addAll(DbWrapper.getnominees());
        if(nominees != null) {
            showNominees(nominees);
        }
    }

    public void showNominees(List<user> l) {
        int row = 1;
        try {
            for ( user u : nominees) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("candidate_demo.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                demoController demo = fxmlLoader.getController();
                demo.setData(u);
                grid.add(anchorPane,0,row++);
                GridPane.setMargin(anchorPane, new Insets(10));

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    public void setChosen () {
//        int i = 0;
//        try {
//            for (i = 0; i < grid.getColumnCount(); i++) {
//                i++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    public void ok() {
        main.closePopup();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("member_dashboard.fxml"));
            root = fxmlLoader.load();
            dashboardcontroller dash = fxmlLoader.getController();
            dash.navigate("member_profile.fxml");
            dash.voteb.setVisible(false);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
        System.out.println("hii");
        main.popupScene("votesubmitted.fxml");



    }
}
