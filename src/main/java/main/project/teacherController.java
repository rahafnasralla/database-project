package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class teacherController implements Initializable {
    private Connection con;
    private final  Base main = new Base();
    private user user = userholder.getInstance().getUser();
    ObservableList<String> sportList = FXCollections.observableArrayList("Tennis","Ping pong","Basketball","track race");
    private ObservableList<course> list = FXCollections.observableArrayList();
    String chosen;
    @FXML
    private TableColumn<course, LocalDate> date;

    @FXML
    private DatePicker dateinput;

    @FXML
    private TableColumn<course, Integer> duration;

    @FXML
    private TextField durationinput;

    @FXML
    private TableColumn<?, ?> enrolled;

    @FXML
    private Label name;

    @FXML
    private PieChart pie;

    @FXML
    private TableColumn<course, Integer> price;

    @FXML
    private TextField priceinput;


    @FXML
    private TableColumn<course, String> sport;

    @FXML
    private ComboBox<String> sportinput = new ComboBox<>();

    @FXML
    private TableView<course> table;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void selected(ActionEvent event) {
        chosen = sportinput.getValue();
    }

    @FXML
    void signout(ActionEvent event) {
        userholder.getInstance().setUser(null);
        main.changeScene("login.fxml");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sportinput.setItems(sportList);
        name.setText("Name : "+user.getFname()+" "+user.getLname());
        date.setCellValueFactory(new PropertyValueFactory<>("START_DATE"));
        price.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
        enrolled.setCellValueFactory(new PropertyValueFactory<>("NO_ENROLLED"));
        sport.setCellValueFactory(new PropertyValueFactory<>("SPORT"));
        duration.setCellValueFactory(new PropertyValueFactory<>("COURSE_DURATION"));
        list.addAll(DbWrapper.getTeacherCourses(user));
        table.setItems(list);
    }
}
