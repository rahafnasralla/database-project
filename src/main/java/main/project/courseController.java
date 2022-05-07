package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class courseController implements Initializable {
    private course c;
    private user user = userholder.getInstance().getUser();
    private static Connection con;
    private static final Base main = new Base();
    private static PreparedStatement stmt;
    private int ID;
    @FXML
    private TableColumn<course, String> asport;

    @FXML
    private TableView<course> availableCourses;

    @FXML
    private TableColumn<course, LocalDate> date;

    @FXML
    private TableColumn<course, String> duration;

    @FXML
    private TableView<course> enrolledcourses;

    @FXML
    private TableColumn<course,String> esport;

    @FXML
    private TableColumn<course, String> eteacher;

    @FXML
    private TableColumn<course, Integer> price;

    @FXML
    private TableColumn<course, String> teacher;
    private ObservableList<course> courseList = FXCollections.observableArrayList();
    private ObservableList<course> ecourseList = FXCollections.observableArrayList();
    @FXML
    void enroll(ActionEvent event) {
        if(!DbWrapper.isCourse(c,user)) {
            try {
                String sql = "INSERT INTO COURSE_MEMBER VALUES (?,?)";
                con = main.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, user.getID());
                stmt.setInt(2, ID);
                String sql0 = "UPDATE COURSE set NO_ENROLLED=? where COURSE_ID = ?";
                PreparedStatement s0 = con.prepareStatement(sql0);
                s0.setInt(1, c.getNO_ENROLLED() + 1);
                s0.setInt(2, ID);
                stmt.executeUpdate();
                s0.executeUpdate();
                con.close();
                stmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ecourseList.add(c);
            enrolledcourses.setItems(ecourseList);
        }
        else
            JOptionPane.showMessageDialog(null,"you are already enrolled");

    }

    @FXML
    void rowSelected(MouseEvent event) {
        c = availableCourses.getSelectionModel().getSelectedItem();
        ID = c.getCOURSE_ID();
        //System.out.println(ID+"");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asport.setCellValueFactory(new PropertyValueFactory<>("SPORT"));
        date.setCellValueFactory(new PropertyValueFactory<>("START_DATE"));
        duration.setCellValueFactory(new PropertyValueFactory<>("COURSE_DURATION"));
        price.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("TEACHER"));
        courseList.addAll(DbWrapper.getCourses());
        availableCourses.setItems(courseList);
        esport.setCellValueFactory(new PropertyValueFactory<>("SPORT"));
        eteacher.setCellValueFactory(new PropertyValueFactory<>("TEACHER"));


    }
}

