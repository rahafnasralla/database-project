package main.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class demoController {
    private Connection con;
    private final Base main = new Base();
    private Parent root;
    boolean first = true;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView img;
    @FXML
    private Button name;
    private user u=new user();
    public boolean s;
    public void setData(user user){
        u.setPnumber(user.getPnumber());
        u.set$address(user.get$address());
        u.setEmail(user.getEmail());
        u.setPass(user.getPass());
        u.setStatus(user.getStatus());
        u.setPhoto(user.getPhoto());
        u.setBlood(user.getBlood());
        u.setID(user.getID());
        u.setBirthdate(user.getBirthdate());
        u.setGender(user.getGender());
        u.setDisable(user.getDisable());
        u.setElect(user.getElect());
        u.setMngflag(user.getMngflag());
        u.setFname(user.getFname());
        u.setLname(user.getLname());
        u.setVotes(user.getVotes());
        name.setText(user.getFname()+" "+user.getLname());
        InputStream ins = new ByteArrayInputStream(user.getPhoto());
        Image photo = new Image(ins);
        if(photo != null) {
            img.setImage(photo);
            img.setFitWidth(122);
            img.setFitHeight(113);
        }
    }
    @FXML
    public void choose() {
        if(first==true) {
            pane.setStyle("-fx-border-color: #000000");
            String sql = "update member set NOVOTES=? where SSN = ?";
            try {
                con = main.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, (u.getVotes() + 1));
                stmt.setInt(2, u.getID());
                stmt.executeUpdate();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            first = false;
        }
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("vote.fxml"));
                root = fxmlLoader.load();
                voteController vote = fxmlLoader.getController();
                vote.submit();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

        }

    }

