package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import oracle.sql.TIMESTAMP;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class eventController implements Initializable {
    private user user = userholder.getInstance().getUser();
    private ObservableList<event> eventList = FXCollections.observableArrayList();
    private static Connection con;
    private static final Base main = new Base();
    @FXML
    private RadioButton attend;
    @FXML
    private RadioButton no;
    @FXML
    private RadioButton yes;
    @FXML
    private ToggleGroup intrest;
    @FXML
    private TableColumn<event, LocalDate> date;

    @FXML
    private TableColumn<event, String> event;

    @FXML
    private TableColumn<event, String> intrested;

    @FXML
    private TableView<event> table;

    @FXML
    private TableColumn<event, Timestamp> time;
    private event ev = new event();

    @FXML
    void rowSelected(MouseEvent e) {
        ev = table.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setCellValueFactory(new PropertyValueFactory<>("EVENT_DATE"));
        event.setCellValueFactory(new PropertyValueFactory<>("EVENT_NAME"));
        intrested.setCellValueFactory(new PropertyValueFactory<>("INTRESTED"));
        time.setCellValueFactory(new PropertyValueFactory<>("TIME"));
        eventList.addAll(DbWrapper.getEvents(user));
        table.setItems(eventList);

    }

    @FXML
    void update(ActionEvent evnt) {
        //check if it exists in the event_member table if it doesn't insert
        String x = new String();
        try {
            con = main.getConnection();
            String sql= "UPDATE EVENT_MEMBER set INTRESTED=? where EVENT = ? and MEMBER=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            if(yes.isSelected()) {
                x= "Interested";
                stmt.setString(1, x);
            }
            else if(no.isSelected()) {
                x="Not Interested";
                stmt.setString(1, x);
            }
            else if(attend.isSelected()) {
                x="Will Attend";
                stmt.setString(1, x);
            }
            stmt.setInt(2,ev.getEVENT_NUMBER());
            stmt.setInt(3,user.getID());
            stmt.executeUpdate();
            con.close();
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ObservableList<event> currentList = table.getItems();
        for (event e : currentList)
        {
            if(e.getEVENT_NUMBER()==ev.getEVENT_NUMBER()) {
                e.setINTRESTED(x);
                table.setItems(currentList);
                table.refresh();
                break;
            }

        }

    }

}