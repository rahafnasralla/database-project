package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class renewController implements Initializable {
    private user user = userholder.getInstance().getUser();
    ObservableList<String> list = FXCollections.observableArrayList("monthly", "yearly");
    private Connection con;
    private final Base main = new Base();
    String x;
    @FXML
    private Label cardno;
    @FXML
    private Label price;
    @FXML
    private Label validfor;
    @FXML
    private ComboBox<String> type = new ComboBox<String>();

    @FXML
    public void renew() {
        String sql = "update membership set PRICE=?,EXPIRING_DATE=? where M_SSN = ?";
        cardno.setText(x);
        try {
            con = main.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            if(type.getValue().equals("monthly")) {
                stmt.setInt(1, 25);
                stmt.setDate(2, Date.valueOf(LocalDate.now().plusYears(1)));
                price.setText(25+"");
                validfor.setText(LocalDate.now().plusYears(1)+"");
            }
            else if(type.getValue().equals("yearly")) {
                stmt.setInt(1, 300);
                stmt.setDate(2, Date.valueOf(LocalDate.now().plusMonths(1)));
                price.setText(300+"");
                validfor.setText(LocalDate.now().plusMonths(1)+"");
            }
            stmt.setInt(2, user.getID());
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems(list);
        try {
            Connection con = main.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from MEMBERSHIP" ;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                if(resultSet.getInt("M_SSN")==user.getID()) {
                    x =resultSet.getInt("CARD_NUMBER") + "";
                    cardno.setText(resultSet.getInt("CARD_NUMBER") + "");
                    price.setText(resultSet.getInt("PRICE") + "");
                    validfor.setText(resultSet.getDate("EXPIRING_DATE") + "");
                }
            }
                stmt.close();
                con.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}

