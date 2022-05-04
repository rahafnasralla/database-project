package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class renewController implements Initializable {
    private user user = userholder.getInstance().getUser();
    ObservableList<String> list = FXCollections.observableArrayList("monthly","yearly");
    private Connection con;
    private final  Base main = new Base();
    @FXML
    private Label cardno;
    @FXML
    private Label price;
    @FXML
    private Label validfor;
    @FXML
    private ComboBox<String> type = new ComboBox<String>();
    @FXML
    public void renew(){
        String sql = "update membership set PRICE=? where M_SSN = ?";
        try {
            con = main.getConnection();
            PreparedStatement stmt = con.prepareStatement( sql);
            stmt.setBytes(1,user.getPhoto());
            stmt.setInt(2, user.getID());
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        cardno.setText(user.getMembership().getCardnumber()+"");
        price.setText(user.getMembership().getPrice()+"");
        validfor.setText(user.getMembership().getExpiring()+"");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems(list);

    }
}
