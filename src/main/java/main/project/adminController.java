package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    private Connection con;
    private final  Base main = new Base();
    private user user = userholder.getInstance().getUser();
    private ObservableList<event> list = FXCollections.observableArrayList();
    event e = new event();
    private static PreparedStatement stmt;
    @FXML
    private TableColumn<event, LocalDate> date;

    @FXML
    private DatePicker dateinput;

    @FXML
    private TableColumn<event, String> name;

    @FXML
    private TextField nameinput;

    @FXML
    private Button submit;

    @FXML
    private TableView<event> table;

    @FXML
    private TableColumn<event, Timestamp> time;

    @FXML
    private TextField timeinput;

    @FXML
    void add(ActionEvent event) {
        dateinput.setVisible(true);
        nameinput.setVisible(true);
        timeinput.setVisible(true);
        submit.setVisible(true);
        submit.setText("Add event");
    }

    @FXML
    void delete(ActionEvent event) {
        if (e!=null) {
            try {
                String sql = "DELETE FROM EVENT WHERE EVENT_NUMBER=?";
                String s = "DELETE FROM EVENT_MEMBER WHERE EVENT=?";
                con = main.getConnection();
                stmt = con.prepareStatement(sql);
                PreparedStatement stmt0 = con.prepareStatement(s);
                stmt0.setInt(1, e.getEVENT_NUMBER());
                stmt.setInt(1, e.getEVENT_NUMBER());
                stmt0.executeUpdate();
                stmt.executeUpdate();
                con.close();
                stmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        table.setItems(refresh());
        table.refresh();
    }

    @FXML
    void reports(ActionEvent event) {
        main.changeScene("reports.fxml");
    }

    @FXML
    void rowSelected(MouseEvent event) {
        e = table.getSelectionModel().getSelectedItem();
    }

    @FXML
    void signout(ActionEvent event) {
        userholder.getInstance().setUser(null);
        main.changeScene("choose.fxml");
    }

    @FXML
    void submitting(ActionEvent event) {
        if (submit.getText().equals("Add event")) {
            try {
                String sql = "INSERT INTO EVENT (EVENT_DATE, EVENT_NAME, TIME)" + "values(?,?,?)";
                con = main.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setString(3, (timeinput.getText()));
                stmt.setString(2, (nameinput.getText()));
                stmt.setDate(1, Date.valueOf(dateinput.getValue()));
                stmt.executeUpdate();
                con.close();
                stmt.close();
                DbWrapper.forEvents(refresh());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (submit.getText().equals("update")) {
            if (e != null) {
                try {
                    String sql = "UPDATE EVENT set EVENT_NAME=?,EVENT_DATE=?,TIME=? where EVENT_NUMBER = ?";
                    con = main.getConnection();
                    stmt = con.prepareStatement(sql);
                    stmt.setString(3, (timeinput.getText()));
                    stmt.setString(1, (nameinput.getText()));
                    stmt.setDate(2, Date.valueOf(dateinput.getValue()));
                    stmt.setInt(4, e.getEVENT_NUMBER());
                    stmt.executeUpdate();
                    con.close();
                    stmt.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        timeinput.setText("");
        nameinput.setText("");
        dateinput.setVisible(false);
        timeinput.setVisible(false);
        nameinput.setVisible(false);
        submit.setVisible(false);
        table.setItems(refresh());
        table.refresh();
    }

    @FXML
    void update(ActionEvent event) {
        dateinput.setVisible(true);
        nameinput.setVisible(true);
        timeinput.setVisible(true);
        submit.setVisible(true);
        dateinput.setValue(e.getEVENT_DATE());
        nameinput.setText(e.getEVENT_NAME());
        timeinput.setText(e.getTIME());
        submit.setText("update");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setCellValueFactory(new PropertyValueFactory<>("EVENT_DATE"));
        time.setCellValueFactory(new PropertyValueFactory<>("TIME"));
        name.setCellValueFactory(new PropertyValueFactory<>("EVENT_NAME"));
        list.addAll(DbWrapper.getAllEvents());
        table.setItems(list);
        dateinput.setVisible(false);
        nameinput.setVisible(false);
        timeinput.setVisible(false);
        submit.setVisible(false);

    }
    public ObservableList<event> refresh() {
        ObservableList<event> newList = FXCollections.observableArrayList();
        newList.addAll(DbWrapper.getAllEvents());
        return newList;
    }


}
