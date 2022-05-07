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
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class teacherController implements Initializable {
    private Connection con;
    private final  Base main = new Base();
    private user user = userholder.getInstance().getUser();
    ObservableList<String> sportList = FXCollections.observableArrayList("Tennis","Ping pong","Basketball","track race","football");
    private ObservableList<course> list = FXCollections.observableArrayList();
    String chosen;
    course c = new course();
    private static PreparedStatement stmt;
    @FXML
    private TableColumn<course, LocalDate> date;
    @FXML
    private Button submitting;

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
        dateinput.setVisible(true);
        sportinput.setVisible(true);
        durationinput.setVisible(true);
        priceinput.setVisible(true);
        submitting.setVisible(true);
        submitting.setText("Add course");

    }
    @FXML
    void submit(ActionEvent event) {
        if(submitting.getText().equals("Add course"))
        {
            try {
                String sql = "INSERT INTO COURSE (SPORT, COURSE_DURATION, NO_ENROLLED, START_DATE, PRICE, T_ID)"+"values(?,?,?,?,?,?)";
                con = main.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setInt(2,Integer.parseInt(durationinput.getText()));
                stmt.setInt(5,Integer.parseInt(priceinput.getText()) );
                stmt.setDate(4, Date.valueOf(dateinput.getValue()));
                //check if not chosen
                stmt.setString(1,sportinput.getValue());
                stmt.setInt(3,0);
                stmt.setInt(6,user.getID());
                stmt.executeUpdate();
                con.close();
                stmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (submitting.getText().equals("update"))
        {
            try {
                String sql = "UPDATE COURSE set COURSE_DURATION=?,PRICE=?,START_DATE=?,SPORT=? where COURSE_ID = ?";
                con = main.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setInt(1,Integer.parseInt(durationinput.getText()));
                stmt.setInt(2,Integer.parseInt(priceinput.getText()) );
                stmt.setDate(3, Date.valueOf(dateinput.getValue()));
                if(sportinput.getValue().isEmpty())
                stmt.setString(4,c.getSPORT() );
                else
                    stmt.setString(4,sportinput.getValue());
                stmt.setInt(5,c.getCOURSE_ID());
                stmt.executeUpdate();
                con.close();
                stmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        dateinput.setVisible(false);
        sportinput.setVisible(false);
        durationinput.setVisible(false);
        priceinput.setVisible(false);
        submitting.setVisible(false);
        table.setItems(refresh());
        table.refresh();
    }

    @FXML
    void update(ActionEvent event) {
        dateinput.setVisible(true);
        sportinput.setVisible(true);
        durationinput.setVisible(true);
        priceinput.setVisible(true);
        submitting.setVisible(true);
        dateinput.setValue(c.getSTART_DATE());
        durationinput.setText(c.getCOURSE_DURATION()+"");
        priceinput.setText(c.getPRICE()+"");
        submitting.setText("update");
    }
    @FXML
    void delete(ActionEvent event) {

        try {
            String sql = "DELETE FROM COURSE WHERE COURSE_ID=?";
            String s = "DELETE FROM COURSE_MEMBER WHERE COURSE=?";
            con = main.getConnection();
            stmt = con.prepareStatement(sql);
            PreparedStatement stmt0 = con.prepareStatement(s);
            stmt0.setInt(1,c.getCOURSE_ID());
            stmt.setInt(1, c.getCOURSE_ID());
            stmt0.executeUpdate();
            stmt.executeUpdate();
            con.close();
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.setItems(refresh());
        table.refresh();

    }

    @FXML
    void selected(ActionEvent event) {
        chosen = sportinput.getValue();
    }

    @FXML
    void signout(ActionEvent event) {
        userholder.getInstance().setUser(null);
        main.changeScene("choose.fxml");
    }
    @FXML
    void selectedRow(MouseEvent event) {
        c = table.getSelectionModel().getSelectedItem();

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
        dateinput.setVisible(false);
        sportinput.setVisible(false);
        durationinput.setVisible(false);
        priceinput.setVisible(false);
        submitting.setVisible(false);

    }
    public ObservableList<course> refresh() {
        ObservableList<course> newList = FXCollections.observableArrayList();
        newList.addAll(DbWrapper.getTeacherCourses(user));
        return newList;
    }
}
